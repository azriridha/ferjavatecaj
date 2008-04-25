package hr.fer.zemris.java.tecaj_9.vjezba;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.*;

import java.io.*;

public class Console {

	public static void main(String[] args) {
	
		File cmdDir = new File("./bin/hr/fer/zemris/java/tecaj_9/vjezba/commands");
		if (!cmdDir.exists())
		{
			System.out.println("ne postoji direktorij sa naredbama");
			System.exit(1);
		}
		
		FilenameFilter filter = new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.endsWith(".class");
			}
		};
		File[] cmdClass = cmdDir.listFiles(filter);
		ICommand cmd[] = new ICommand[cmdClass.length];
		
		for (int i = 0; i < cmdClass.length; i++)
		{
			try{
				String name = cmdClass[i].getName();
				name = name.substring(0, name.length() - ".class".length());
				cmd[i] = (ICommand)Class
				.forName("hr.fer.zemris.java.tecaj_9.vjezba.commands." + name)
				.newInstance();
			} catch (InstantiationException e) {
				System.out.println("Nije uspjela inicijalizacija klase");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("Klasa nije dostupna");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Klasa nije pronadjena");
				e.printStackTrace();
			} 
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String[] splitLine;
		while(true)
		{
			System.out.print("> ");
			try {
				line = br.readLine();
				if (line.equals("exit"))
					break;
				splitLine = line.split(" ");
				String[] params = new String[splitLine.length-1];
				System.arraycopy(splitLine, 1, params, 0, params.length);
				
				if (splitLine[0].equals("help"))
				{
					printHelp(cmd, params);
					continue;
				}
				int i;
				for (i = 0; i < cmd.length; i++)
				{
					if (splitLine[0].equals(cmd[i].getCommandName()))
					{
						try{
							cmd[i].execCommand(params, System.out);
						} catch (CommandExecException e){
							System.out.println(e.getMessage());
						} catch (IllegalArgumentException e){
							System.out.println(e.getMessage());
							System.out.println("Usage:");
							System.out.println("\t" + cmd[i].getCommandDescription());
						}
						break;
					}
				}
				//ako nije pronadjena naredba
				if (i == cmd.length)
					System.out.println("Nepoznata naredba");
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	private static void printHelp(ICommand[] cmd, String[] arg)
	{
		if (arg.length == 0)
		{
			for (int i = 0; i < cmd.length; i++)
			{
				printCommandHelp(cmd[i]);
			}
			return;
		}
		if (arg.length > 1)
		{
			System.out.println("Pogresan broj argumenata");
			return;
		}
		for (int i = 0; i < cmd.length; i++)
			if (cmd[i].getCommandName().equals(arg[0]))
			{
				printCommandHelp(cmd[i]);
				return;
			}
		System.out.println("Nepoznata naredba");
	}
	
	private static void printCommandHelp(ICommand cmd)
	{
		System.out.println("Command: " + cmd.getCommandName());
		System.out.println("-------------------------------");
		System.out.println(cmd.getCommandDescription());
		System.out.println();
	}

}

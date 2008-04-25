package hr.fer.zemris.java.tecaj_9.vjezba.commands;

import java.io.*;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;

public class Ls implements ICommand {

	@Override
	public void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException {
		if (parameters != null && parameters.length > 1)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		
		if (parameters.length == 1)
		{
			File f = new File(parameters[0]);
			if (!f.exists())
				throw new CommandExecException("Trazeni direktorij ne postoji");
			if (!f.isDirectory())
				throw new CommandExecException(f.getName() + " nije direktorij");
			listFiles(f);
		}
		else 
			listFiles(new File("."));
	}
	
	private void listFiles(File f)
	{
		File[] list = f.listFiles();
		for (int i = 0; i < list.length; i++)
		{
			if (list[i].isDirectory())
				System.out.print("[DIR] ");
			else 
				System.out.print("[FIL] ");
			System.out.println(list[i].getName());
		}
	}
	@Override
	public String getCommandDescription() {
		return "ls [dir]" +
				"Lists directory.\n" +
				"If no argument is given, lists current directory.\n" +
				"If one argument is given, lists that directory.";
	}

	@Override
	public String getCommandName() {
		return "ls";
	}

}

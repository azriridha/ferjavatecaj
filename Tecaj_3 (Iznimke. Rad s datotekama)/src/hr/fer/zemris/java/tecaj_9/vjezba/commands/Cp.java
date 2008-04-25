package hr.fer.zemris.java.tecaj_9.vjezba.commands;

import java.io.*;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;

public class Cp implements ICommand {

	@Override
	public void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException {
		
		BufferedOutputStream bos;
		BufferedInputStream bis;
		
		if (parameters.length != 2)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		
		File src = new File(parameters[0]);
		if (!src.exists())
			throw new CommandExecException("Izvorisna datoteka ne postoji");
		File dest = new File(parameters[1]);
		if (dest.exists())
			throw new CommandExecException("Odredisna datoteka vec postoji");
		if(src.isDirectory() || dest.isDirectory())
			throw new CommandExecException("Odrediste i izvoriste moraju biti datoteke");
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
		} catch (FileNotFoundException e) {
			throw new CommandExecException("Izvorisna datoteka ne moze biti otvorena za citanje");
		}
		try {
			bos = new BufferedOutputStream(new FileOutputStream(dest));
		} catch (FileNotFoundException e) {
			throw new CommandExecException("Odredisna datoteka ne moze biti stvorena");
		}
		
		byte[] buf = new byte[(int)src.length()];
		
		try {
			bis.read(buf);
		} catch (IOException e) {
			throw new CommandExecException("Ne mogu procitati izvorisnu datoteku");
		} finally{
			try{
				bis.close();
			} catch(IOException ignorable){}
		}
		try {
			bos.write(buf);
		} catch (IOException e) {
			throw new CommandExecException("Ne mogu zapisati u odredisnu datoteku");
		}finally{
			try{
				bos.close();
			} catch(IOException ignorable){}
		}
		System.out.println("Kopiranje uspjesno zavrseno");
	}

	@Override
	public String getCommandDescription() {
		return "cp <izvorna datoteka> <odredisna datoteka>\n" +
				"kopira datoteku (samo ako odrediste ne postoji)";
	}

	@Override
	public String getCommandName() {
		return "cp";
	}

}

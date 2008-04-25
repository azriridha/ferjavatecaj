package hr.fer.zemris.java.tecaj_9.vjezba.commands;

import java.io.*;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;

public class Rm implements ICommand {

	@Override
	public void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException {
		
		if (parameters.length != 1)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		
		File f = new File(parameters[0]);
		if (!f.exists())
			throw new CommandExecException("datoteka ili direktorij ne postoji");
		
		brisi(f);
		System.out.println(f.getName() + " uspjesno obrisan");
	}
	
	private void brisi(File f)
	{
		if (f.isDirectory())
		{
			File[] list = f.listFiles();
			for (int i = 0; i < list.length; i++)
				brisi(list[i]);
		}
		f.delete();
	}

	@Override
	public String getCommandDescription() {
		return "rm <datoteka>|<direktorij>\n" +
				"brise datoteku ili direktorij, " +
				"zajedno sa svim poddirektorijima";
	}

	@Override
	public String getCommandName() {
		return "rm";
	}

}

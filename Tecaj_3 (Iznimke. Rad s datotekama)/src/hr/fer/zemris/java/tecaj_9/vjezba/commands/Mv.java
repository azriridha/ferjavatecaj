package hr.fer.zemris.java.tecaj_9.vjezba.commands;

import java.io.*;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;

public class Mv implements ICommand {

	@Override
	public void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException {
		
		if (parameters.length != 2)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		
		File src = new File(parameters[0]);
		if (!src.exists())
			throw new CommandExecException("Izvorisna datoteka ne postoji");
		File dest = new File(parameters[1]);
		if (dest.exists())
			throw new CommandExecException("Odredisna datoteka vec postoji");
		
		if( src.renameTo(dest) == true )
			System.out.println("Datoteka uspjesno premjestena");
		else
			System.out.println("Nije uspjelo premjestanje datoteke");
	}

	@Override
	public String getCommandDescription() {
		return "mv <izvorna datoteka> <odredisna datoteka>\n" +
				"premjesta datoteku, samo ako odrediste ne postoji";
	}

	@Override
	public String getCommandName() {
		return "mv";
	}

}

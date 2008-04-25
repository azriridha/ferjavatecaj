package hr.fer.zemris.java.tecaj_9.vjezba.commands;

import java.io.PrintStream;
import java.util.Date;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;

public class Time implements ICommand {
	
	public void execCommand(String[] parameters, PrintStream out)
		throws CommandExecException, IllegalArgumentException
	{
		if(parameters!=null && parameters.length>0) {
			throw new IllegalArgumentException("Unexpected arguments!");
			}
		out.println(new Date().toString());
	}
	
	public String getCommandDescription() {
		return "Returns current date and time. Expects no arguments.";	
	}
	
	public String getCommandName() {
		return "date";
	}
}
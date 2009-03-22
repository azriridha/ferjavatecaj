package hr.fer.grafovi.model;

public class WrongGraphException extends Exception {

	private static final long serialVersionUID = 8175821273484243170L;

	public WrongGraphException()
	{
		super();
	}
	
	public WrongGraphException(String message)
	{
		super(message);
	}

}

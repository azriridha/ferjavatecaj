/**
 * 
 */
package hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces;

/**
 * Iznimka koja se može dogoditi u slučaju izvršavanja neke naredbe.
 */
public class CommandExecException extends Exception {

	private static final long serialVersionUID = 3654857255719903223L;

	/**
	 * Konstruktor.
	 */
	public CommandExecException() {
	}

	/**
	 * Konstruktor.
	 * @param message poruka koja objasnjava uzrok iznimke
	 */
	public CommandExecException(String message) {
		super(message);
	}

	/**
	 * Konstruktor za ulancavanje iznimki.
	 * @param message iznimka - uzrok
	 */
	public CommandExecException(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstruktor za ulancavanje iznimki uz dodatni opis.
	 * @param message opis iznimke
	 * @param cause iznimka - uzrok
	 */
	public CommandExecException(String message, Throwable cause) {
		super(message, cause);
	}
}

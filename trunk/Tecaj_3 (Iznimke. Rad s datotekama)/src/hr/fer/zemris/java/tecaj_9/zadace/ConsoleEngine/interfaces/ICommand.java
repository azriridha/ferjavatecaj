/**
 * 
 */
package hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces;

import java.io.PrintStream;

/**
 * Sucelje koje implementira svaka naredba (1 naredba = 1 razred) konzole.
 * 
 * @author Garfield
 * 
 */
public interface ICommand {
	/**
	 * Dohvaca ime naredbe koja se implementira ovim suceljem
	 * 
	 * @return ime naredbe (paziti na velika i mala slova!)
	 */
	String getCommandName();

	/**
	 * Izvrsava naredbu koja implementira ovo sucelje
	 * 
	 * @param parameters
	 *            parametri naredbe
	 * @param out
	 *            (ne nuzno) standardni izlaz na koji se ispisuje rezultat
	 *            izvodenja naredbe
	 * @throws CommandExecException
	 *             u slucaju neke neocekivane greske, metoda mora vratiti ovu
	 *             iznimku
	 * @throws IllegalArgumentException
	 *             u slucaju da je naredbi predan pogresan ili nepotpun
	 *             parametar, metoda mora vratiti ovu iznimku
	 */
	void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException;

	/**
	 * Vraca opis naredbe koja implementira ovo sucelje (bilo bi pozeljno
	 * pozivati nakon što metoda <code>execCommand</code> vrati
	 * <code>IllegalArgumentException</code>)
	 * 
	 * @return opis naredbe
	 */
	String getCommandDescription();
}

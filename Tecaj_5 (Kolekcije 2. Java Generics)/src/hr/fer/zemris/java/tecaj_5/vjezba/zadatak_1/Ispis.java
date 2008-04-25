package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

import java.io.IOException;

/**
 * The Interface Ispis.
 */
public interface Ispis {
	
	/**
	 * Zapisi.
	 * 
	 * @param file the file
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void zapisi(String file) throws IOException;
}

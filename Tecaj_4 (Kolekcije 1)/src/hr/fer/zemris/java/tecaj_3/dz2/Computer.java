package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * @author marcupic
 *
 * Sucelje razreda koji predstavlja podatkovni
 * dio racunala.
 */
public interface Computer {
	/**
	 * Metoda koja vraca registre racunala
	 * @return registre
	 */
	public Registers getRegisters();
	/**
	 * Metoda koja vraca memoriju racunala
	 * @return memoriju racunala
	 */
	public Memory getMemory();
}

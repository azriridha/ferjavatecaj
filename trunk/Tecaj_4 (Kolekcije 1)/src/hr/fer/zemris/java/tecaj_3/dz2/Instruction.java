package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * Ovo sucelje predstavlja jednu instrukciju.
 * 
 * @author marcupic
 */
public interface Instruction {
	/**
	 * Pozivom ove metode izvrsava se sama instrukcija.
	 * 
	 * @param computer racunalo
	 * @return true ako treba zaustaviti procesor,
	 *         false inace
	 */
	public boolean execute(Computer computer);
}

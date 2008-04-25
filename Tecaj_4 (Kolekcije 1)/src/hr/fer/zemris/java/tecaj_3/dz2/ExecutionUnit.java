package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * Upravljacki sklop racunala. Ovaj razred "izvodi"
 * program odnosno predstavlja impulse takta za sam
 * procesor.<br>
 * 
 * Sucelje definira samo jednu metodu {@linkplain #go(Computer)}}
 * ciji se pseudo-kod moze zapisati ovako:
 * <pre>
 *   stavi program counter na 0
 *   ponavljaj do beskonacnosti
 *   | dohvati instrukciju iz memorije s lokacije program countera
 *   | uvecaj program counter za 1
 *   | izvrsi instrukciju; ako je instrukcija vratila true, prekini petlju
 *   kraj petlje
 *   vrati true
 * </pre>
 * 
 * @author marcupic 
 */
public interface ExecutionUnit {
	/**
	 * Metoda ciji je zadatak izvoditi program zapisan
	 * u memoriji racunala.
	 * 
	 * @param computer racunalo
	 * @return true ako je program zavrsio regularno,
	 *         false ako se je dogodila iznimka
	 */
	public boolean go(Computer computer);
}

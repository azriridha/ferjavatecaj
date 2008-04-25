package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * Argument instrukcije koji stvara parser na temelju
 * asemblerskog zapisa. Sluzi za konstruktor svake
 * instrukcije. 
 * 
 * @author marcupic
 */
public interface InstructionArgument {
	/**
	 * Provjera je li vrijednost zapravo indeks registra.
	 * Ako ova metoda vrati true, tada poziv {@linkplain #getValue()}
	 * vraca objekt tipa Integer koji cuva indeks registra.
	 * @return true ako je vrijednost indeks registra, false inace
	 */
	public boolean isRegister();
	/**
	 * Provjera je li vrijednost zapravo neki string (tekst).
	 * Ako ova metoda vrati true, tada poziv {@linkplain #getValue()}
	 * vraca objekt tipa String koji cuva upisani tekst.
	 * @return true ako je vrijednost tekst, false inace
	 */
	public boolean isString();
	/**
	 * Provjera je li vrijednost zapravo neki broj.
	 * Ako ova metoda vrati true, tada poziv {@linkplain #getValue()}
	 * vraca objekt tipa Integer koji cuva upisani broj.
	 * @return true ako je vrijednost broj, false inace
	 */
	public boolean isNumber();
	/**
	 * Metoda za dohvat same vrijednosti. Sto ce tocno biti vraceni
	 * objekt, moze se prethodno provjeriti pozivima metoda
	 * {@linkplain #isNumber()}, {@linkplain #isRegister()} te
	 * {@linkplain #isString()}. 
	 * @return vrijednost argumenta instrukcije
	 */
	public Object getValue();
}

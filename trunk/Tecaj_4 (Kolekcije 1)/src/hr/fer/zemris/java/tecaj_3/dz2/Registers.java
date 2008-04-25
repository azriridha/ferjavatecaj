package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * Sucelje koje predstavlja sve registre u procesoru.
 * Postoje registri opce namjene za ciji rad sluze metode
 * {@linkplain #getRegisterValue(int)} i {@linkplain #setRegisterValue(int, Object)},
 * programsko brojilo koje predstavlja adresu sljedece instrukcije koju
 * treba izvesti (metode {@linkplain #getProgramCounter()}, 
 * {@linkplain #incrementProgramCounter()}, {@linkplain #setProgramCounter(int)}),
 * te registar zastavice (metode {@linkplain #getFlag()}, {@linkplain #setFlag(boolean)}).
 * Za razliku od klasicnih procesorskih registara koji pohranjuju samo
 * brojeve (i fiksne su sirine), registri opce namjene mogu pohranjivati
 * proizvoljno velike objekte razlicitih tipova (Stringove, Integere).
 * 
 * @author marcupic
 */
public interface Registers {
	/**
	 * Dohvaca objekt pohranjen u zadanom registru.
	 * 
	 * @param index broj registra; brojevi idu od 0.
	 * @return objekt pohranjen u tom registru
	 */
	public Object getRegisterValue(int index);
	/**
	 * Metoda u zadani registar upisuje predani objekt.
	 * 
	 * @param index broj registra; brojevi idu od 0.
	 * @param value vrijednost koju treba upisati
	 */
	public void setRegisterValue(int index, Object value);
	/**
	 * Metoda za ocitavanje vrijednosti programskog brojila.
	 * 
	 * @return vrijednost programskog brojila
	 */
	public int getProgramCounter();
	/**
	 * Metoda za postavljanje vrijednosti programskog brojila.
	 * 
	 * @param value nova vrijednost programskog brojila
	 */
	public void setProgramCounter(int value);
	/**
	 * Metoda uvecava vrijednost programskog brojila za 1.
	 */
	public void incrementProgramCounter();
	/**
	 * Metoda za dohvat vrijednosti zastavice.
	 * 
	 * @return vrijednost zastavice
	 */
	public boolean getFlag();
	/**
	 * Metoda za postavljanje vrijednosti zastavice.
	 * 
	 * @param value nova vrijednost zastavice
	 */
	public void setFlag(boolean value);
}

package hr.fer.zemris.java.tecaj_3.dz2;

/**
 * Sucelje koje predstavlja memoriju racunala. Za razliku
 * od klasicne memorije racunala koja je bajtno orijentirana,
 * nasa memorija na svakoj lokaciji moze pohraniti proizvoljno
 * veliki objekt, a koristit cemo Stringove i Integere.
 * 
 * @author marcupic
 */
public interface Memory {
	/**
	 * Metoda za postavljanje vrijednosti na zadanu lokaciju.
	 * @param location memorijska adresa
	 * @param value objekt koji treba pohraniti
	 */
	public void setLocation(int location, Object value);
	/**
	 * Metoda za dohvat vrijednosti sa zadane lokacije.
	 * @param location memorijska adresa
	 * @return objekt pohranjen na trazenoj lokaciji
	 */
	public Object getLocation(int location);
}

package hr.fer.zemris.java.tecaj_5.vjezba.zad1;

/**
 * Opci semafor
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class OSem {
	
	private int brojac;
	
	/**
	 * Instancira novi opco semafor
	 * 
	 * @param n inicijalna vrijednost brojaca
	 */
	public OSem(int n)
	{
		brojac = n;
	}
	
	/**
	 * Metoda koja umanjuje trenutnu vrijednost brojaca za n, ali samo ako nova vrijednost brojaca 
	 * ne bi poprimila negativnu vrijednost. Ako bi prilikom umanjivanja vrijednost postala negativna 
	 * dretva se blokira i ostaje blokirana sve dok umanjivanje ne uspije
	 * 
	 * @param n vrijednost za koju se umanjuje brojac
	 * @throws InterruptedException
	 */
	public synchronized void get(int n) throws InterruptedException 
	{
		if (n < 0)
			throw new IllegalArgumentException("n ne smije biti negativan");
		while (n > this.brojac)
			wait();
		brojac -= n;
	}
	
	/**
	 * Metoda koja vrijednost brojaca povecava za n
	 * 
	 * @param n vrijednost za koju se brojac povacava
	 * @throws InterruptedException
	 */
	public synchronized void post(int n) throws InterruptedException 
	{
		if (n < 0)
			throw new IllegalArgumentException("n ne smije biti negativan");
		brojac += n;
		this.notify();
	}

}

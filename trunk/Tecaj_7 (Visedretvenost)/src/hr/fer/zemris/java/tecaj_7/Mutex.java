package hr.fer.zemris.java.tecaj_7;

/**
 * Razred koji uporabom monitorskih funkcija implementira
 * sinkronizacijski mehanizam mutex.
 * 
 * @author marcupic
 *
 */
public class Mutex {

	/**
	 * Indikacija je li netko u kriticnom odsjecku 
	 */
	private boolean netkoJeUnutra = false;
	
	/**
	 * Zahtjev za ulaskom u kriticni odsjecak. Dretva ce
	 * napustiti ovu metodu tek kada niti jedna druga dretva
	 * nije trenutno u kriticnom odsjecku.
	 * 
	 * @throws InterruptedException
	 */
	public void udi() throws InterruptedException {
		synchronized(this) {
			while(netkoJeUnutra) {
				this.wait();
			}
			netkoJeUnutra = true;
		}
	}
	
	/**
	 * Metoda kojom dretva javlja da je gotova s kriticnim
	 * odsjeckom, i da neka druga dretva sada moze biti
	 * pustena.
	 */
	public void izadi() {
		synchronized(this) {
			netkoJeUnutra = false;
			this.notify();
		}
	}
}

package hr.fer.zemris.java.tecaj_7;

/**
 * Ilustracija pokretanja dretve, gdje je dretva novi
 * razred koji nasljeduje Thread.
 * 
 * @author marcupic
 */
public class PokretacDretve1 {

	/**
	 * Pocetak programa
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		Dretva1 dretva = new Dretva1();
		dretva.setName("Druga dretva");
		System.out.println("Idem pokrenuti novu dretvu!");
		dretva.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignorable) {
		}
		Thread.currentThread().getThreadGroup().list();
		System.out.println("Glavna dretva je gotova!");
		dretva = new Dretva1();
		dretva.start();
	}

}

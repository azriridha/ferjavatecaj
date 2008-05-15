package hr.fer.zemris.java.tecaj_5.vjezba.zad2;

import hr.fer.zemris.java.tecaj_5.vjezba.zad1.OSem;

/**
 * Filozof stalno ciklicki obavlja isti posao. Prvo jede, pa misli. Jelo i razmisljanje je simulirano
 * spavanjem od 2 sekunde. Prije nego filozof krene jesti, uzima onoliko vilica sa stola koliko ima ruku.
 * Uzimanje vilica je regulirano opcim semaforom.
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class Filozof implements Runnable {

	private int brojRuku;
	private OSem sem;
	
	public Filozof(int brojRuku, OSem sem) {
		this.brojRuku = brojRuku;
		this.sem = sem;
	}

	/**
	 * metoda koja se poziva prilikom pokretanjadretve filozofa. Simulira zivot filozofa. Zivot filozofa se
	 * sastoji samo od jela i razmisljanja. 
	 */
	@Override
	public void run() {
		while(true)
		{
			try {
				misli();
				sem.get(brojRuku);
				jedi();
				sem.post(brojRuku);
			} catch (InterruptedException ignorable) {}
		}		
	}
	
	/**
	 * metoda koja simulira jelo filozofa
	 * 
	 * @throws InterruptedException
	 */
	private void jedi() throws InterruptedException
	{
		ispis("jedem");
		Thread.sleep(2000);
		ispis("idem misliti");
	}
	
	/**
	 * metoda koja simulira razmisljanje filozofa
	 * 
	 * @throws InterruptedException
	 */
	
	private void misli() throws InterruptedException
	{
		ispis("mislim");
		Thread.sleep(2000);
		ispis("idem jesti");
	}
	
	/**
	 * pomocna metoda za ispis teksta. ispisuje se ime dretve i poruka
	 * 
	 * @param text poruka koja se ispisuje uz ime dretve
	 */
	private void ispis(String text)
	{
		System.out.println(Thread.currentThread().getName() +	": " + text);
	}
	
}

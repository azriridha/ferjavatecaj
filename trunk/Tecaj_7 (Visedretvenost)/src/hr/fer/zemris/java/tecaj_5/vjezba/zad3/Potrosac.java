package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.Random;

/**
 * Dretva Potrosac. Periodicki stavlja cijele brojeve u kolekciju zadataka
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class Potrosac extends Thread {
	
	private int donjaGranica;
	private int velicinaIntervala;
	private Zadaci<Integer> zadaci;
	
	/**
	 * Instancira novog potrosaca. Racuna se donja granica intervala i velicina intervala 
	 * iz parametara E i F 
	 * 
	 * @param E srednja vrijednost cekanja
	 * @param F maksimalno odstupanje od srednje vrijednosti
	 * @param zadaci kolekcija zadataka
	 */
	public Potrosac(int E, int F, Zadaci<Integer> zadaci)
	{
		if (E < F || E < 0 || F < 0)
			throw new IllegalArgumentException("vrijeme cekanja ne smije biti negativno");
		this.donjaGranica = (E - F) * 1000;
		this.velicinaIntervala = (2 * F + 1) * 1000;
		this.zadaci = zadaci;
	}
	
	/**
	 * glavna metoda koja pokrece dretvu
	 */
	@Override
	public void run()
	{
		int broj;
		Random rnd = new Random();
		while(true)
		{
			try
			{
				broj = zadaci.skiniZadatak();
				System.out.println("preuzeo sam zadatak " + broj);
				sleep(donjaGranica + rnd.nextInt(velicinaIntervala));
				System.out.println(broj + " na kvadrat iznosi " + broj*broj);
			} catch (InterruptedException ignorable){}
		}
	}

}

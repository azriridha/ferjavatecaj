package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.Random;
/**
 * Dretva proizvodjac. Periodicno uzima cijele brojeve iz kolekcije zadataka i ispisuje njihove 
 * kvadrate na ekran
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class Proizvodjac extends Thread {

	private int donjaGranica;
	private int velicinaIntervala;
	private Zadaci<Integer> zadaci;

	/**
	 * Instancira novog proizvodjaca. Racuna se donja granica intervala i velicina intervala 
	 * iz parametara B i C 
	 * 
	 * @param B srednja vrijednost cekanja
	 * @param C maksimalno odstupanje od srednje vrijednosti
	 * @param zadaci kolekcija zadataka
	 */
	public Proizvodjac(int B, int C, Zadaci<Integer> zadaci)
	{
		if (B < C || B < 0 || C < 0)
			throw new IllegalArgumentException("vrijeme cekanja ne smije biti negativno");
		this.donjaGranica = (B - C) * 1000;
		this.velicinaIntervala = (2 * C + 1)* 1000;
		this.zadaci = zadaci;
	}

	/**
	 * glavna metoda koja pokrece dretvu
	 */
	@Override
	public void run()
	{
		Random rnd = new Random();
		int broj;
		while (true)
		{
			try
			{
				sleep(donjaGranica + rnd.nextInt(velicinaIntervala));
				broj = rnd.nextInt(20) + 1;
				System.out.println("ubacujem zadatak " + broj);
				zadaci.dodajZadatak(broj);
				System.out.println("ubacio sam zadatak " + broj);
			} catch (InterruptedException ignorable){}
		}
	}
}

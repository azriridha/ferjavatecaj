package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.Random;

public class Proizvodjac extends Thread {

	private int donjaGranica;
	private int velicinaIntervala;
	private Zadaci<Integer> zadaci;

	public Proizvodjac(int B, int C, Zadaci<Integer> zadaci)
	{
		if (B < C || B < 0 || C < 0)
			throw new IllegalArgumentException("vrijeme cekanja ne smije biti negativno");
		this.donjaGranica = (B - C) * 1000;
		this.velicinaIntervala = (2 * C + 1)* 1000;
		this.zadaci = zadaci;
	}

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

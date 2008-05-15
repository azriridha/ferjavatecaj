package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.Random;


public class Potrosac extends Thread {
	
	private int donjaGranica;
	private int velicinaIntervala;
	private Zadaci<Integer> zadaci;
	
	public Potrosac(int E, int F, Zadaci<Integer> zadaci)
	{
		if (E < F || E < 0 || F < 0)
			throw new IllegalArgumentException("vrijeme cekanja ne smije biti negativno");
		this.donjaGranica = (E - F) * 1000;
		this.velicinaIntervala = (2 * F + 1) * 1000;
		this.zadaci = zadaci;
	}
	
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

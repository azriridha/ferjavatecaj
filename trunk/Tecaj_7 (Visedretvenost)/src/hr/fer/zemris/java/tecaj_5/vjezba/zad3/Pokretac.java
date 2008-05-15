package hr.fer.zemris.java.tecaj_5.vjezba.zad3;


public class Pokretac {

	/**
	 * Glavni program koji pokrece proizvodjace i potrosace ciji je broj zadan u ulaznim argumentima
	 * ima 6 ulaznih argumenata, A, B, C, D, E, F, koji su cijeli brojevi. Znacenje je slijedece
	 * 
	 * @param args String koji se sastoji od 6 argumenata odvojenih razmakom
	 * A predstavlja broj proizvodjaca koje treba pokrenuti
	 * B predstavlja srednju vrijednost cekanja prije no sto proizvodjac proizvede neki zadatak
	 * C predstavlja maksimalno odstupanje od srednje vrijednosti B
	 * D predstavlja broj potrosaca koje treba pokrenuti
	 * E predstavlja srednju vrijednost cekanja prije no sto potrosac obradi neki zadatak
	 * F predstavlja maksimalno odstupanje od srednje vrijednosti E
	 */
	public static void main(String[] args)
	{
		if (args.length != 6)
			throw new IllegalArgumentException("pogresan poziv programa, ocekuje se 6 argumenata");
		int A = Integer.parseInt(args[0]);
		int B = Integer.parseInt(args[1]);
		int C = Integer.parseInt(args[2]);
		int D = Integer.parseInt(args[3]);
		int E = Integer.parseInt(args[4]);
		int F = Integer.parseInt(args[5]);
		
		Zadaci<Integer> zadaci = new Zadaci<Integer>(5);
		Thread t;
		for (int i = 0; i < A; i++)
		{
			t = new Proizvodjac(B, C, zadaci);
			t.start();
		}
		
		for (int i = 0; i < D; i++)
		{
			t = new Potrosac(E, F, zadaci);
			t.start();
		}
	}

}

package hr.fer.zemris.java.tecaj_5.vjezba.zad2;

import hr.fer.zemris.java.tecaj_5.vjezba.zad1.OSem;

public class Glavni {
	
	public static void main(String[] args) {

		if (args.length != 2)
			throw new IllegalArgumentException("pogresan broj argumenata pri pozivu programa");
		
		int brojVilica = Integer.parseInt(args[0]);
		OSem sem = new OSem(brojVilica);
		String[] ruke = args[1].split(",");
		Thread t;
		for (int i = 0; i < ruke.length; i++)
		{
			t = new Thread(new Filozof(Integer.parseInt(ruke[i]), sem), "Filozof " + i);
			t.start();
		}
	}

}

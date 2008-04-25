package hr.fer.zemris.java.tecaj_4;

import java.util.*;

public class Bonus {

	public static void main(String[] args) {
		
		Map<Zaposlenik,Double> bonusi = new HashMap<Zaposlenik,Double>();
		
		bonusi.put(new Zaposlenik("1", "Peric", "Pero"), Double.valueOf(100.0));
		bonusi.put(new Zaposlenik("2", "Agic", "Agata"), Double.valueOf(200.0));
		bonusi.put(new Zaposlenik("3", "Ivic", "Ivana"), Double.valueOf(300.0));
		
		for (Zaposlenik zap : bonusi.keySet())
		{
			Double bonus = bonusi.get(zap);
			System.out.println(zap + " ima bonus od : " + bonus + "kn.");
		}
		
		Double bonusOd1 = bonusi.get(new Zaposlenik("1","Peric","Pero"));
		System.out.println("Bonus zaposlenika cija je sifra 1 je " + bonusOd1 + "kn.");
	}

}

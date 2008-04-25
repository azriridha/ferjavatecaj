package hr.fer.zemris.java.tecaj_2.vjezba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Zadatak02_03 {
	
	private static Slika slika = new Slika(40,40);

	public static void main(String[] args) {

		BufferedReader br;
		String line;
		String[] row;

		try{
			br = new BufferedReader(new FileReader(args[0]));
			while ((line = br.readLine()) != null)
			{
				row = line.split("\\s");
				obradi(row);
			}
			br.close();
		} catch (IOException e){
			System.out.println("Ne mogu otvoriti datoteku");
			System.exit(31);
		} 
		
		slika.nacrtajSliku(System.out);
	}
	
	
	private static void obradi(String[] row)
	{
		GeometrijskiLik lik;
		String[] params = new String[row.length - 1];
		System.arraycopy(row, 1, params, 0, row.length-1);
		try {
			if (row[0].equalsIgnoreCase("linija"))
			{
				lik = Linija.fromStringArray(params);
				Linija linija = (Linija)lik;
				linija.popuniLik(slika);
			}
			else if (row[0].equalsIgnoreCase("pravokutnik"))
			{
				lik = Pravokutnik.fromStringArray(params);
				Pravokutnik pravokutnik = (Pravokutnik)lik;
				pravokutnik.popuniLik(slika);
			}
			else if (row[0].equalsIgnoreCase("kvadrat"))
			{
				lik = Kvadrat.fromStringArray(params);
				Kvadrat kvadrat = (Kvadrat)lik;
				kvadrat.popuniLik(slika);
			}
			else if (row[0].equalsIgnoreCase("elipsa"))
			{
				lik = Elipsa.fromStringArray(params);
				Elipsa elipsa = (Elipsa)lik;
				elipsa.popuniLik(slika);
			}
			else if (row[0].equalsIgnoreCase("kruznica"))
			{
				lik = Kruznica.fromStringArray(params);
				Kruznica kruznica = (Kruznica)lik;
				kruznica.popuniLik(slika);
			}
			else 
				throw new ClassNotFoundException("Nepoznat razred");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(31);
		} 	
	}
	
}

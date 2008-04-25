package hr.fer.zemris.java.tecaj_2.vjezba;

import java.io.*;
import java.text.DecimalFormat;

public class Zadatak02_02 {

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
			System.exit(11);
		} 
		
	}
	
	
	private static void obradi(String[] row)
	{
		GeometrijskiLik lik;
		DecimalFormat df = new DecimalFormat("0000.00");
		String[] params = new String[row.length - 1];
		System.arraycopy(row, 1, params, 0, row.length-1);
		try {
			if (row[0].equalsIgnoreCase("linija"))
			{
				lik = Linija.fromStringArray(params);
				Linija linija = (Linija)lik;
				System.out.println(
						linija.toString() + " " +
						df.format(linija.getOpseg()) + " " +
						df.format(linija.getPovrsina())
						);
			}
			else if (row[0].equalsIgnoreCase("pravokutnik"))
			{
				lik = Pravokutnik.fromStringArray(params);
				Pravokutnik pravokutnik = (Pravokutnik)lik;
				System.out.println(
						pravokutnik.toString() + " " +
						df.format(pravokutnik.getOpseg()) + " " +
						df.format(pravokutnik.getPovrsina())
						);
			}
			else if (row[0].equalsIgnoreCase("kvadrat"))
			{
				lik = Kvadrat.fromStringArray(params);
				Kvadrat kvadrat = (Kvadrat)lik;
				System.out.println(
						kvadrat.toString() + " " +
						df.format(kvadrat.getOpseg()) + " " +
						df.format(kvadrat.getPovrsina())
						);
			}
			else if (row[0].equalsIgnoreCase("elipsa"))
			{
				lik = Elipsa.fromStringArray(params);
				Elipsa elipsa = (Elipsa)lik;
				System.out.println(
						elipsa.toString() + " " +
						df.format(elipsa.getOpseg()) + " " +
						df.format(elipsa.getPovrsina())
						);
			}
			else if (row[0].equalsIgnoreCase("kruznica"))
			{
				lik = Kruznica.fromStringArray(params);
				Kruznica kruznica = (Kruznica)lik;
				System.out.println(
						kruznica.toString() + " " +
						df.format(kruznica.getOpseg()) + " " +
						df.format(kruznica.getPovrsina())
						);
			}
			else 
				throw new ClassNotFoundException("Nepoznat razred");
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
			
	}
	
}

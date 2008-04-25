package hr.fer.zemris.java.tecaj_1.vjezba;

import java.text.DecimalFormat;

public class Zadatak01_01 {

	public static void main(String[] args) {
		
		double[] polje = new double[args.length];
		
		for (int i = 0; i < args.length; i++)
		{
			polje[i] = Double.parseDouble(args[i]);
		}
		
		DecimalFormat formatter = new DecimalFormat("0.000");
		
		System.out.println("Aritmeticka sredina iznosi " + formatter.format(aritmetickaSredina(polje)) + ".");
		System.out.println("Geometrijska sredina iznosi " + formatter.format(geometrijskaSredina(polje)) + ".");
		System.out.println("Harmonijska sredina iznosi " + formatter.format(harmonijskaSredina(polje)) + ".");
		
	}
	
	public static double aritmetickaSredina(double[] polje)
	{
		if (polje.length == 0) return 0;
		double zbroj = 0;
		for (int i = 0; i < polje.length; i++)
		{
			zbroj += polje[i];
		}
		return zbroj/polje.length;
	}
	
	public static double geometrijskaSredina(double[] polje)
	{
		if (polje.length == 0) return 0;
		double umnozak  = 1;
		for (int i = 0; i < polje.length; i++)
		{
			umnozak *= polje[i];
		}
		return Math.pow(umnozak, 1.0/polje.length);
	}
	
	public static double harmonijskaSredina(double[] polje)
	{
		double zbroj = 0;
		for (int i = 0; i < polje.length; i++)
		{
			if (polje[i] != 0)
				zbroj += 1/polje[i];
		}
		if (zbroj == 0) return 0;
		return polje.length/zbroj;
	}

}

package hr.fer.zemris.java.tecaj_1.vjezba;

import java.io.*;

public class Zadatak01_03 {

	public static void main(String[] args) throws IOException {
		
		System.out.print("Unesite broj elemenata polja: ");
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in)
			);
		
		int brojElemenata = Integer.parseInt(reader.readLine());
		int[] polje = new int[brojElemenata];
		
		for (int i = 0; i < brojElemenata; i++)
		{
			System.out.println("Unesite " + (i+1) + ". element: ");
			polje[i] = Integer.parseInt(reader.readLine());
		}
		
		System.out.println("Najmanji broj je " + najmanji(polje));
		
	}
	
	public static int najmanji(int[] polje)
	{
		int min = polje[0];
		for (int i = 1; i < polje.length; i++)
		{
			if (polje[i] < min)
				min = polje[i];
		}
		return min;
	}
	
	

}

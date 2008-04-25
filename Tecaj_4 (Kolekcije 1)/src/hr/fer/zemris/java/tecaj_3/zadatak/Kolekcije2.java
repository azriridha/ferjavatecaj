package hr.fer.zemris.java.tecaj_3.zadatak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Kolekcije2 {


	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in)
			);
		
		String line = "";
		double temp;
		List<Double> list = new LinkedList<Double>();
		

		while (true)
		{
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line.equals("quit"))
				break;
			try {
				temp = Double.parseDouble(line);	
			} catch (NumberFormatException e){
				e.printStackTrace();
				continue;
			}
			list.add(temp);
		}
		
		double avg = prosjek(list);
		
		Collections.sort(list);
		
		for (double d : list)
		{
			if (d >= 1.2 * avg)
				System.out.println(d);
		}
		

	}
	private static double prosjek(List<Double> list)
	{
		double sum = 0;
		int i = 0;
		
		for (double d : list)
		{
			sum += d;
			i++;
		}
		return sum/i;
		
	}
}

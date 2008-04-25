package hr.fer.zemris.java.tecaj_3.zadatak;

import java.io.*;
import java.util.*;

public class Kolekcije1 {

	public static void main(String[] args) {

		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		try {
			reader1 = new BufferedReader(new FileReader(args[0]));
			reader2 = new BufferedReader(new FileReader(args[1]));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}	
		
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		String line;
		
		try {
			while ((line = reader1.readLine()) != null)
			{
				set1.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			while ((line = reader2.readLine()) != null)
			{
				set2.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		set1.removeAll(set2);
		
		for (String s : set1)
		{
			System.out.println(s);
		}
		
	}

}

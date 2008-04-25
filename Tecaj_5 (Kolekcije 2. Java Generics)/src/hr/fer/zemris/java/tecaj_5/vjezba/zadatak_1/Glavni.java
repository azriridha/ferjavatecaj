package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

import java.io.*;
import java.util.*;

/**
 * The Class Glavni.
 */
public class Glavni {

	private static Map<String,Student> studenti = new HashMap<String, Student>();
	private static Map<String,Grupa> grupe = new HashMap<String,Grupa>();
	private static BodovnaPolitika bp;
	private static List<Obrazac> obrasci = new LinkedList<Obrazac>();
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		try {
			studenti = ucitajStudente("studenti.txt");
			grupe = ucitajGrupe("grupe.txt");
			bp = ucitajBodovnuPolitiku("bodovna_politika.txt");
			obrasci = ucitajObrasce("ocitano.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalArgumentException e){
			e.printStackTrace();
			System.exit(1);
		} 
		Detaljno detaljno = new Detaljno((LinkedList<Obrazac>)obrasci);
		Ispis sumarno = new Sumarno(obrasci);
		Ispis objava = new Objava(obrasci);
		Ispis najbolji = new Najbolji(obrasci);
		try {
			detaljno.zapisi("detaljno.txt");
			detaljno.zapisiXLS("proba.xls");
			sumarno.zapisi("sumarno.txt");
			objava.zapisi("objava.txt");
			najbolji.zapisi("najbolji.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Funkcija koja ucitava studente iz datoteke u mapu. Kljuc mape je jmbag studenta
	 * 
	 * @param file the file
	 * 
	 * @return the map< string, student>
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Map<String,Student> ucitajStudente(String file) throws IOException
	{
		Map<String,Student> studenti = new HashMap<String,Student>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line;
		String[] arg;
		while ((line = br.readLine()) != null)
		{
			arg = line.split("\t");
			if (arg.length != 3)
				throw new IllegalArgumentException("Pogresan broj stupaca u datoteci " + file);
			studenti.put(arg[0], new Student(arg[0], arg[1], arg[2]));
		}
		br.close();
		return studenti;
	}
	
	/**
	 * Funkcija koja ucitava grupe iz datoteke u mapu. Kljuc mape je oznaka grupe
	 * 
	 * @param file the file
	 * 
	 * @return the map< string, grupa>
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Map<String,Grupa> ucitajGrupe(String file) throws IOException
	{
		Map<String,Grupa> grupe = new HashMap<String,Grupa>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line;
		String[] arg;
		while ((line = br.readLine()) != null)
		{
			arg = line.split("\t");
			// u datoteci mora biti neparan broj stupaca: ime grupe + parovi broj bodova i tocan odgovor
			if (arg.length % 2 == 0)
				throw new IllegalArgumentException("Pogresan broj stupaca u datoteci " + file);
			int brojZadataka = (arg.length - 1)/2;
			Grupa grupa = new Grupa(arg[0], brojZadataka);
			for (int i = 0; i < brojZadataka; i++)
			{
				if (arg[2*i + 2].length() != 1)
					throw new IllegalArgumentException("Greska kod zapisa tocnog odgovora u datoteci " + file);
				grupa.setMaxBodovi(i, Double.parseDouble(arg[2*i + 1]));
				grupa.setTocniOdgovori(i, arg[2*i + 2].charAt(0));
			}
			grupe.put(grupa.getOznakaGrupe(), grupa);
		}
		br.close();
		return grupe;
	}
	
	/**
	 * Ucitaj bodovnu politiku.
	 * 
	 * @param file the file
	 * 
	 * @return the bodovna politika
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static BodovnaPolitika ucitajBodovnuPolitiku(String file) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line = br.readLine();
		String[] arg = line.split("\t");
		if (arg.length != 3)
			throw new IllegalArgumentException("Pogresan broj stupaca u datoteci " + file);
		br.close();
		return new BodovnaPolitika(
				fractionToDouble(arg[0]), 
				fractionToDouble(arg[1]), 
				fractionToDouble(arg[2])
				);
	}
	
	/**
	 * Funkcija koja razlomak pretvara u decimalni broj
	 * 
	 * @param s the s
	 * 
	 * @return the double
	 */
	private static double fractionToDouble(String s){
		if (s.contains("/"))
		{
			String[] split = s.split("/");
			if (split.length != 2)
				throw new NumberFormatException(s + "nije broj");
			return Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
		}
		return Double.parseDouble(s);
	}
	
	/**
	 * Funkcija koja ucitava obrasce iz datoteke u listu
	 * 
	 * @param file the file
	 * 
	 * @return the list< obrazac>
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static List<Obrazac> ucitajObrasce(String file) throws IOException
	{
		List<Obrazac> obrasci = new LinkedList<Obrazac>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line;
		String[] arg;
		String[] odgovori;
		while ((line = br.readLine()) != null)
		{
			arg = line.split("\t");
			odgovori = new String[arg.length-2];
			System.arraycopy(arg, 2, odgovori, 0, arg.length - 2);
			obrasci.add(new Obrazac(
					studenti.get(arg[0]),
					grupe.get(arg[1]),
					bp,
					odgovori)
			);
		}
		br.close();
		return obrasci;
	}
}

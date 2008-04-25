package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

import java.io.*;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Klasa najbolji sluzi za zapisivanje studenata koji su ostvarili
 * barem 90% na ispitu
 * @author HrvojeTorbasinovic
 */
public class Najbolji implements Ispis {

List<Obrazac> obrasci;
	
	/**
	 * Instantiates a new najbolji.
	 * 
	 * @param obrasci the obrasci
	 */
	public Najbolji(List<Obrazac> obrasci)
	{
		this.obrasci = new LinkedList<Obrazac>(obrasci);
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1.Ispis#zapisi(java.lang.String)
	 */
	@Override
	public void zapisi(String file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
		Collections.sort(obrasci, new Komparator());
		DecimalFormat f = new DecimalFormat("0.00");
		double maxBodovi;
		for (Obrazac obrazac : obrasci)
		{
			maxBodovi = izracunajMax(obrazac.getGrupa());
			if (obrazac.getUkupniBodovi() < 0.9 * maxBodovi)
				break;
			bw.write(
					obrazac.getStudent().getJmbag() + "\t" + 
					obrazac.getStudent().getPrezime() + "\t" +
					obrazac.getStudent().getIme() + "\t" + 
					f.format(obrazac.getUkupniBodovi())
					);
			bw.newLine();
			
		}
		bw.close();
	}
	
	/**
	 * Racuna maksimalan broj bodova u grupi
	 * 
	 * @param grupa the grupa
	 * 
	 * @return the double
	 */
	private double izracunajMax(Grupa grupa)
	{
		double[] maxBodovi = grupa.getMaxBodovi();
		double sum = 0;
		for (double d : maxBodovi)
			sum += d;
		return sum;
	}
	
	/**
	 * Komparator sortira po broju bodova, potom po prezimenu, imenu 
	 * pa jmbag-u
	 */
	private class Komparator implements Comparator<Obrazac>
	{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Obrazac arg0, Obrazac arg1) {
			if (arg0 == null){
				if (arg1 == null)
					return 0;
				return -1;
			} else if (arg1 == null)
				return 1;
			
			double res = arg0.getUkupniBodovi() - arg1.getUkupniBodovi();
			if (res < 0)
				return 1;
			else if (res > 0)
				return -1;
			
			Collator hrCollator = Collator.getInstance(new Locale("hr"));
			int rez = hrCollator.compare(arg0.getStudent().getPrezime(), arg1.getStudent().getPrezime());
			if (rez != 0)
				return rez;
			rez = hrCollator.compare(arg0.getStudent().getIme(), arg1.getStudent().getIme());
			if (rez != 0)
				return rez;
			return arg0.getStudent().getJmbag().compareTo(arg1.getStudent().getJmbag());
		}
		
	}
}

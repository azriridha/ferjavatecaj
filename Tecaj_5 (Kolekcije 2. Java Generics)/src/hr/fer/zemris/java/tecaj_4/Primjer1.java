package hr.fer.zemris.java.tecaj_4;

import java.util.*;

public class Primjer1 {

	public static void main(String[] args) {
		
		Set<Zaposlenik> lista = new TreeSet<Zaposlenik>();
		
		lista.add(new Zaposlenik("2", "Agic", "Agata"));
		lista.add(new Zaposlenik("3", "Ivic", "Ivana"));
		lista.add(new Zaposlenik("1", "Peric", "Pero"));
		
		for (Zaposlenik zap : lista){
			System.out.println(zap);
		}
		Zaposlenik zaposlenik = new Zaposlenik("1","Peric","Pero");
		boolean sadrziZaposlenika = lista.contains(zaposlenik);
		System.out.println("SadrziZaposlenika = " + sadrziZaposlenika);
	}

	public static class Komparator1 implements Comparator<Zaposlenik>{

		@Override
		public int compare(Zaposlenik arg0, Zaposlenik arg1) {
			if (arg0 == null)
			{
				if (arg1 == null) return 0;
				return -1;
			} else if (arg1 == null) 
				return 1;
			
			long res = Long.parseLong(arg0.getSifra()) - Long.parseLong(arg1.getSifra());
			if (res < 0) return -1;
			else if (res > 0) return 1;
			else return 0;
		}
		
	}
}

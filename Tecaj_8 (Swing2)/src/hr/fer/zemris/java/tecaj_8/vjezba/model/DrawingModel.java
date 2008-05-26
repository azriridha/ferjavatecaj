package hr.fer.zemris.java.tecaj_8.vjezba.model;

import hr.fer.zemris.java.tecaj_8.vjezba.Line;

import java.util.Set;
import java.util.TreeSet;

/**
 * Razred koji predstavlja model podataka. Za cuvanje linija koristi se sucelje Set.
 * Za implementaciju sucelja Set je odabran razred TreeSet jer omogucuje cuvanje poretka
 * koji se koristi u iscrtavanju.
 * 
 * @author Hrvoje
 */
public class DrawingModel extends AbstractModel{

	Set<Line> linije;
	
	/**
	 * Instancira novi model
	 */
	public DrawingModel(){
		linije = new TreeSet<Line>();
	}

	/**
	 * Dohvaca skup linija
	 * 
	 * @return Skup linija
	 */
	public Set<Line> getLinije()
	{
		return linije;
	}


	/**
	 * Dodaje liniju u skup linija
	 * @param l Linija koja se dodaje
	 */
	public void addLine(Line l)
	{
		linije.add(l);
		firePropertyChange("addLine", null, linije);
	}
	
	/**
	 * Brise liniju iz skupa linija
	 * @param linija
	 */
	public void removeLine(Line linija)
	{
		linije.remove(linija);
		firePropertyChange("removeLine", null, linije);
	}
	
	/**
	 * Salje obavijest da je doslo do promjene u podacima
	 */
	public void refresh()
	{
		firePropertyChange("refresh", null, linije);
	}
}

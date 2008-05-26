package hr.fer.zemris.java.tecaj_8.vjezba.controller;

import hr.fer.zemris.java.tecaj_8.vjezba.Line;

/**
 * Razred koji predstavlja kontroler
 * 
 * @author Hrvoje
 *
 */
public class DrawingController extends AbstractController{
	
	/**
	 * Instancira novi kontroler
	 */
	public DrawingController()
	{
		super();
	}

	/**
	 * dodaje novu liniju u modele
	 * @param linija Linija koja se dodaje
	 */
	public void addLine(Line linija)
	{
		callModelMethod("addLine", linija);
	}
	
	/**
	 * Brise liniju iz modela
	 * @param linija Linija koja se brise
	 */
	public void removeLine(Line linija)
	{
		callModelMethod("removeLine", linija);
	}
	
	/**
	 * Metoda koja kaze modelima da posalju obavijest da je doslo do promjene.
	 * Koriste je pogledi kada zele obavijestiti druge poglede da je doslo do promjene
	 */
	public void refresh()
	{
		callModelMethod("refresh");
	}
}

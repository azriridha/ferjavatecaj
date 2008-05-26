package hr.fer.zemris.java.tecaj_8.vjezba;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Razred koji predstavlja jednu liniju. Linija ima svoj pocetak, kraj, boju i redni broj 
 * 
 * @author Hrvoje
 *
 */
public class Line implements Comparable<Line> {

	private static int brojLinija = 0;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color boja;
	private int redniBroj;
	
	/**
	 * Instancira novu liniju
	 * 
	 * @param x1 x koordinata pocetne tocke
	 * @param y1 y koordinata pocetne tocke
	 * @param x2 x koordinata krajnje tocke
	 * @param y2 y koordinata krajnje tocke
	 * @param boja boja linije
	 */
	public Line(int x1, int y1, int x2, int y2, Color boja)
	{
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.boja = boja;
		this.redniBroj = brojLinija;
		brojLinija++;
	}

	@Override
	public int compareTo(Line arg0)
	{
		if (arg0 == null)
			return 1;
		return redniBroj - arg0.redniBroj;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Line other = (Line) obj;
		if (redniBroj != other.redniBroj)
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "Linija " + redniBroj +
			": (" + x1 + ", " + y1 + ")" +
			" (" + x2 + ", " + y2 + ")" +
			" Color: " +
			"r=" + boja.getRed() + ",g=" + boja.getGreen() + ",b=" + boja.getBlue();
	}
	
	/**
	 * funkcija za iscrtavanje linije
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.setColor(boja);
		g.drawLine(x1, y1, x2, y2);
	}

	
	public void setX2(int x2)
	{
		this.x2 = x2;
	}

	
	public void setY2(int y2)
	{
		this.y2 = y2;
	}

	
	public int getX1()
	{
		return x1;
	}

	
	public void setX1(int x1)
	{
		this.x1 = x1;
	}

	
	public int getY1()
	{
		return y1;
	}

	
	public void setY1(int y1)
	{
		this.y1 = y1;
	}

	
	public int getX2()
	{
		return x2;
	}

	
	public int getY2()
	{
		return y2;
	}
}

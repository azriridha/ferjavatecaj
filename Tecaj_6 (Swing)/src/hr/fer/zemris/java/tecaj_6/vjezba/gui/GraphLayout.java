package hr.fer.zemris.java.tecaj_6.vjezba.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
/**
 * Ova klasa implementira LayoutManager koji pozicionira komponente u lijevi, desni
 * ili sredisnji dio Container-a
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class GraphLayout implements LayoutManager {
	  
	public static final String SOUTH = "South";
	public static final String WEST = "West";
	public static final String CENTER = "Center";

	private Component south;
	private Component west;
	private Component center;

	// Some constants for use with calcSize().
	private static final int MIN = 0;
	private static final int MAX = 1;
	private static final int PREF = 2;
	
	/**
	 * Dodaje komponentu u layout na specificiranu poziciju koja mora biti
	 * jedna od string konstanti definiranih u ovoj klasi
	 *
	 * @param constraints pozicija
	 * @param component komponenta koja se dodaje
	 *
	 * @exception IllegalArgumentException Ako pozicija nije jedna od
	 * 			  specificiranih u klasi
	 */
	@Override
	public void addLayoutComponent(String constraints, Component component)
	{
		String str = constraints;
		
		if (str == null || str.equals(CENTER))
			center = component;
		else if (str.equals(SOUTH))
			south = component;
		else if (str.equals(WEST))
			west = component;
		else
			throw new IllegalArgumentException("Constraint value not valid: " + str);
	}

	/**
	 * Pozicionira komponente za iscrtavanje u Containeru
	 * 
	 * @param target Container u kojem ce se pozicionirati komponente
	 */
	@Override
	public void layoutContainer(Container target)
	{
		Insets i = target.getInsets();
		int top = i.top;
		int bottom = target.getHeight() - i.bottom;
		int left = i.left;
		int right = target.getWidth() - i.right;
		
		if (south != null)
		{
			Dimension s = calcCompSize(south, PREF);
			Dimension w = calcCompSize(west, PREF);
			south.setBounds(left + w.width, bottom - s.height, right - (left + w.width), s.height);
			bottom -= s.height;
		}
		
		if (west != null)
		{
			Dimension w = calcCompSize(west, PREF);
			west.setBounds(left, top, w.width, bottom - top);
			left += w.width;
		}
		
		if (center != null)
			center.setBounds(left, top, right - left, bottom - top);
	}
	
	/**
	 * Pomocna funkcija koja vraca najmanju, najvecu ili preferiranu 
	 * velicinu komponente
	 */
	private Dimension calcCompSize(Component comp, int what)
	{
		if (comp == null || ! comp.isVisible())
			return new Dimension(0, 0);
		if (what == MIN)
			return comp.getMinimumSize();
		else if (what == MAX)
			return comp.getMaximumSize();
		return comp.getPreferredSize();
	}
	
	/**
	 * Pomocna funkcija koja sluzi izracunavanju raznih velicina containera
	 */
	private Dimension calcSize(Container target, int what)
	{
		Insets ins = target.getInsets();
		
		Dimension sdim = calcCompSize(south, what);
		Dimension wdim = calcCompSize(west, what);
		Dimension cdim = calcCompSize(center, what);
		
		int width = wdim.width;
		
		if (sdim.width > cdim.width)
			width += sdim.width;
		else width += cdim.width;
		
		
		width += (ins.left + ins.right);
		
		int height = sdim.height;
		
		if (wdim.height > cdim.height)
			height += wdim.height;
		else height += cdim.height; 
			
		height += ins.top + ins.bottom;
		
		return(new Dimension(width, height));
	}
	
	/**
	 * Vraca najmanju velicinu Containera koji koristi ovaj layout
	 *
	 * @param target Container za koji se racuna najmanja velicina.
	 *
	 * @return najmanja velicina Container-a
	 */
	@Override
	public Dimension minimumLayoutSize(Container target)
	{
		return calcSize(target, MIN);
	}

	/**
	 * Vraca preferiranu velicinu Containera koji koristi ovaj layout
	 *
	 * @param target Container za koji se racuna preferirana velicina.
	 *
	 * @return preferirana velicina Container-a
	 */
	@Override
	public Dimension preferredLayoutSize(Container target)
	{
		return calcSize(target, PREF);
	}

	/**
	 * Mice specificiranu komponentu iz layouta
	 *
	 * @param component komponenta koju treba maknuti
	 */
	@Override
	public void removeLayoutComponent(Component arg0)
	{
		if (south == arg0)
			south = null;
		if (west == arg0)
			west = null;
		if (center == arg0)
			center = null;
	}
}

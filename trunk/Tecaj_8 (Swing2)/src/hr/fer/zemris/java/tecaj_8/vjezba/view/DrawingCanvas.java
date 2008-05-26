package hr.fer.zemris.java.tecaj_8.vjezba.view;

import hr.fer.zemris.java.tecaj_8.vjezba.Line;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JComponent; 

/**
 * Razred izveden iz JComponent, prikazuje linije. 
 * 
 * @author Hrvoje
 */
public class DrawingCanvas extends JComponent {

	private static final long serialVersionUID = 1L;

	Set<Line> linije;
	/**
	 * Instancira novi primjerak razreda
	 */
	public DrawingCanvas()
	{
		super();
	}
	
	/**
	 * Preferirana velicina prozora
	 */
	public Dimension getPreferredSize() {
        return new Dimension(450,400);
    }

	/**
	 * Iscrtava komponentu
	 */
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (linije == null)
        	return;
        for (Line l : linije)
        	l.draw(g);
    }

	/**
	 * Postavlja skup linija koje se trebaju iscrtati
	 * 
	 * @param linije Linije koje se iscrtavaju
	 */	
	public void setLinije(Set<Line> linije)
	{
		this.linije = linije;
	}
  
}

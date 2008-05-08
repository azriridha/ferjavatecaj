package hr.fer.zemris.java.tecaj_6.vjezba.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Klasa koja sluzi za demonstraciju Graph layouta
 * 
 * @author Hrvoje Torbasinovic
 */
public class GraphLayoutDemo extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public GraphLayoutDemo()
	{
		initGUI();
	}
	
	/**
	 * Stvara tri Panel-a razlicitih boja radi demonstracije GraphLayout-a
	 */
	private void initGUI()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setBackground(Color.RED);
		p2.setBackground(Color.GREEN);
		p3.setBackground(Color.BLUE);
		p1.setPreferredSize(new Dimension(60,200));
		p2.setPreferredSize(new Dimension(150,20));
		this.getContentPane().setLayout(new GraphLayout());
		this.getContentPane().add(p1,GraphLayout.WEST);
		this.getContentPane().add(p2,GraphLayout.SOUTH);
		this.getContentPane().add(p3,GraphLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		try {
			SwingUtilities.invokeAndWait(
					new Runnable(){
						public void run(){
							new GraphLayoutDemo();
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

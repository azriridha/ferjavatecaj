package hr.fer.zemris.java.tecaj6novo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Prozor3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Prozor3()
	{
		initGUI();
	}
	
	protected void initGUI()
	{
		setLocation(20,200);
		setSize(500,200);
		setTitle("Prozor3");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Hello world");
		label.setOpaque(true);
		label.setBackground(Color.GREEN);
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.getContentPane().add(label, BorderLayout.NORTH);
		
		JButton gumb = new JButton("Klikni me");
		this.getContentPane().add(gumb, BorderLayout.CENTER);
		
		this.pack();
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		try {
			SwingUtilities.invokeAndWait(
					new Runnable(){
						public void run(){
							new Prozor3();
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

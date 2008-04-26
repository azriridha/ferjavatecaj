package hr.fer.zemris.java.tecaj6novo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Prozor5 extends JFrame {

	private JLabel label;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Prozor5()
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
		
		label = new JLabel("Hello world");
		label.setOpaque(true);
		label.setBackground(Color.GREEN);
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.getContentPane().add(label, BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new GridLayout(1,4));
		
		JButton gumb = new JButton("Klikni me!");
		panel.add(gumb);
		gumb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				pritisnutGumb1();
			}
		});
		
		gumb = new JButton("Klikni me 2!");
		panel.add(gumb);
		gumb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				pritisnutGumb2();
			}
		});
		
		gumb = new JButton("Klikni me 3!");
		panel.add(gumb);
		gumb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				pritisnutGumb3();
			}
		});
		
		gumb = new JButton("Klikni me 4!");
		panel.add(gumb);
		gumb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				pritisnutGumb4();
			}
		});
		
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		this.pack();
		setVisible(true);
	}
		protected void pritisnutGumb1(){
			label.setText("pritisnut je prvi gumb");
		}
	
		protected void pritisnutGumb2(){
			label.setText("pritisnut je drugi gumb");
		}
		
		protected void pritisnutGumb3(){
			label.setText("pritisnut je treci gumb");
		}
		
		protected void pritisnutGumb4(){
			label.setText("pritisnut je cetvrti gumb");
		}
	
	public static void main(String[] args) 
	{
		try {
			SwingUtilities.invokeAndWait(
					new Runnable(){
						public void run(){
							new Prozor5();
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

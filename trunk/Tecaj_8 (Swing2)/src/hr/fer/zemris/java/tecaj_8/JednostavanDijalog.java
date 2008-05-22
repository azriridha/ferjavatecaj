package hr.fer.zemris.java.tecaj_8;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JednostavanDijalog {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		System.out.println("metodu main izvodi dretva " + Thread.currentThread().getName());
		SwingUtilities.invokeAndWait(new Runnable(){
			@Override
			public void run()
			{
				System.out.println("metodu run izvodi dretva " + Thread.currentThread().getName());
				JTextField poruka = new JTextField("Inicijalna vrijednost");
				int rezultat = JOptionPane.showConfirmDialog(null, poruka, "molim unesite ime", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (rezultat == JOptionPane.OK_OPTION){
					System.out.println("odabran je ok");
					JOptionPane.showMessageDialog(null, "Bok, " + poruka.getText());
				} else {
					System.out.println("odabran je cancel");
				}
				
				String s = JOptionPane.showInputDialog("Molim unesite prezime");
				System.out.println(s);
				JTextField poruka2 = new JTextField("Inicijalna vrijednost");
				JOptionPane.showMessageDialog(null, poruka2, "Pozdravna poruka", JOptionPane.WARNING_MESSAGE);
				System.out.println("korisnik je unio: " + poruka2.getText());
			}
		});
	}
}

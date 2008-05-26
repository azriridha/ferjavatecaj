package hr.fer.zemris.java.tecaj_8.vjezba;

import hr.fer.zemris.java.tecaj_8.vjezba.controller.DrawingController;
import hr.fer.zemris.java.tecaj_8.vjezba.model.DrawingModel;
import hr.fer.zemris.java.tecaj_8.vjezba.view.DrawingView1;
import hr.fer.zemris.java.tecaj_8.vjezba.view.DrawingView2;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * Razred koji sadrzi main metodu, DrawingView1 i DrawingView2 te pokrece program
 * @author Hrvoje
 *
 */
public class Pokretac {
	
	public Pokretac()
	{
		DrawingController dc = new DrawingController();
		DrawingModel dm = new DrawingModel();
		DrawingView1 dw1 = new DrawingView1();
		dw1.pack();
		DrawingView2 dw2 = new DrawingView2();
		dw2.pack();
		
		dc.addModel(dm);
		dc.addView(dw1);
		dc.addView(dw2);
		
		dw2.setController(dc);
		dw2.setVisible(true);
		dw2.setLocationRelativeTo(null);
		

		Point p = dw2.getLocationOnScreen();
		p.translate(dw2.getSize().width, (dw2.getSize().height - dw1.getSize().height)/2);
		dw1.setController(dc);
		dw1.pack();
		dw1.setLocation(p);
		dw1.setVisible(true);
		
		
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new WindowsLookAndFeel());
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new Pokretac();
			}
		});
	}

}

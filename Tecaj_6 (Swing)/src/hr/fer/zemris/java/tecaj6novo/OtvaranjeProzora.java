package hr.fer.zemris.java.tecaj6novo;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class OtvaranjeProzora {

	public static void main(String[] args) {
	JFrame frame = new JFrame();
	
	frame.setLocation(20,200);
	frame.setSize(500,200);
	frame.setTitle("Otvaranje prozora");
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setVisible(true);
	}

}

package hr.fer.zemris.java.tecaj_7;

/**
 * Razred koji ilustrira kako napraviti novu dretvu
 * nasljedivanjem razreda Thread.
 * 
 * @author marcupic
 */
public class Dretva1 extends Thread {
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ignorable) {
		}
		System.out.println("Hello from new thread!");
	}
	
}

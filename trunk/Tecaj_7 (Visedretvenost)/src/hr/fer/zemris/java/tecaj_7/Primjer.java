package hr.fer.zemris.java.tecaj_7;

public class Primjer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread dretva = Thread.currentThread();
		System.out.println("ime> " + dretva.getName());
		System.out.println("jesam ziva> " + dretva.isAlive());
		System.out.println("jesam daemon> " + dretva.isDaemon());
		System.out.println("prioritet> " + dretva.getPriority());
		System.out.println("stanje> " + dretva.getState());
		
		ThreadGroup g = dretva.getThreadGroup();
		g.list();
		while (g.getParent() != null)
			g = g.getParent();
		g.list();
	}

}

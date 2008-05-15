package hr.fer.zemris.java.tecaj_5.vjezba.zad1;

public class OSem {
	
	private int brojac;
	
	public OSem(int n)
	{
		brojac = n;
	}
	
	public synchronized void get(int n) throws InterruptedException 
	{
		if (n < 0)
			throw new IllegalArgumentException("n ne smije biti negativan");
		while (n > this.brojac)
			wait();
		brojac -= n;
	}
	
	public synchronized void post(int n) throws InterruptedException 
	{
		if (n < 0)
			throw new IllegalArgumentException("n ne smije biti negativan");
		brojac += n;
		this.notify();
	}

}

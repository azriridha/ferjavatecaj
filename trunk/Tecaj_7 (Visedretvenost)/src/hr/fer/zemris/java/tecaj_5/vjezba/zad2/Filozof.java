package hr.fer.zemris.java.tecaj_5.vjezba.zad2;

import hr.fer.zemris.java.tecaj_5.vjezba.zad1.OSem;

public class Filozof implements Runnable {

	private int brojRuku;
	private OSem sem;
	
	public Filozof(int brojRuku, OSem sem) {
		this.brojRuku = brojRuku;
		this.sem = sem;
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				sem.get(brojRuku);
				jedi();
				sem.post(brojRuku);
				misli();
			} catch (InterruptedException ignorable) {}
		}		
	}
	
	private void jedi() throws InterruptedException
	{
		ispis("jedem");
		Thread.sleep(2000);
		ispis("pojeo sam");
	}
	
	private void misli() throws InterruptedException
	{
		ispis("mislim");
		Thread.sleep(2000);
		ispis("gladan sam");
	}
	
	private void ispis(String text)
	{
		System.out.println(Thread.currentThread().getName() +	": " + text);
	}
	
}

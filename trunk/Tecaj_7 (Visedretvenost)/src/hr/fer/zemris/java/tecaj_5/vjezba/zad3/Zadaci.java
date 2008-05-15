package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.LinkedList;

public class Zadaci<T> {

	LinkedList<T> zadaci;
	int maxVelicina;
	
	public Zadaci(int maxVelicina)
	{
		if(maxVelicina <= 0)
			throw new IllegalArgumentException("Velicina spremnika mora biti veca od 0");
		this.maxVelicina = maxVelicina;
		zadaci = new LinkedList<T>();
	}
	
	public synchronized void dodajZadatak(T zadatak) throws InterruptedException
	{
			while(zadaci.size() >= maxVelicina)
				wait();
			zadaci.add(zadatak);
			notifyAll();
	}
	
	public synchronized T skiniZadatak() throws InterruptedException
	{
			while(zadaci.size() == 0)
				wait();
			T zadatak = zadaci.removeFirst();
			notifyAll();
			return zadatak;
	}
	
}

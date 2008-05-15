package hr.fer.zemris.java.tecaj_5.vjezba.zad3;

import java.util.LinkedList;
/**
 * Razred koji predstavlja kolekciju zadataka zadanog tipa neke maksimalne velicine
 * 
 * @author Hrvoje Torbasinovic
 *
 * @param <T>
 */
public class Zadaci<T> {

	LinkedList<T> zadaci;
	int maxVelicina;
	/**
	 * Instancira novu kolekciju zadataka neke maksimalne velicine
	 * 
	 * @param maxVelicina maksimalna velicina kolekcije zadataka
	 */
	public Zadaci(int maxVelicina)
	{
		if(maxVelicina <= 0)
			throw new IllegalArgumentException("Velicina spremnika mora biti veca od 0");
		this.maxVelicina = maxVelicina;
		zadaci = new LinkedList<T>();
	}
	
	/**
	 * Dodaje zadatak u kolekciju. Metoda je sinkronizirana. Ako je kolekcija puna, 
	 * dretva koja dodaje zadatak ceka da se oslobodi mjesto u kolekciji. Na kraju metode
	 * se bude sve dretve koje su cekale na koristenje kolekcije
	 * 
	 * @param zadatak zadatak koji se dodaje
	 * @throws InterruptedException
	 */
	public synchronized void dodajZadatak(T zadatak) throws InterruptedException
	{
			while(zadaci.size() >= maxVelicina)
				wait();
			zadaci.add(zadatak);
			notifyAll();
	}
	
	/**
	 * Uzima zadatak iz kolekciju. Metoda je sinkronizirana. Ako je kolekcija prazna, 
	 * dretva koja uzima zadatak ceka da se doda novi zadatak u kolekciji. Na kraju metode
	 * se bude sve dretve koje su cekale na koristenje kolekcije
	 * 
	 * @return zadatak uzet iz kolekcije
	 * @throws InterruptedException
	 */
	public synchronized T skiniZadatak() throws InterruptedException
	{
			while(zadaci.size() == 0)
				wait();
			T zadatak = zadaci.removeFirst();
			notifyAll();
			return zadatak;
	}
	
}

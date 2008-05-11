package hr.fer.zemris.java.tecaj_7;

/**
 * Ilustracija rjesenja problema koji nastaju pri paralelnom uvecavanju
 * iste varijable uporabom kriticnog odsjecka.
 * 
 * @author marcupic
 */
public class ParalelnoUvecavanje2 {

	/**
	 * Brojac koji uvecavamo.
	 */
	private static volatile long brojac = 0;
	/**
	 * Konstanta koja odreduje koliko dretvi zelimo pokrenuti.
	 */
	private static final int ZELJENI_BROJ_DRETVI = 5;
	/**
	 * Sinkronizacijski objekt. 
	 */
	private static Mutex mutex;
	
	/**
	 * Pokretanje programa.
	 * 
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Trenutna vrijednost brojaca je: "+brojac+".");

		mutex = new Mutex();
		
		PosaoDretve posao = new PosaoDretve(1000000, mutex);
		
		Thread[] dretve = new Thread[ZELJENI_BROJ_DRETVI];
		for(int i=0; i<dretve.length; i++) {
			dretve[i] = new Thread(posao);
		}

		for(int i=0; i<dretve.length; i++) {
			dretve[i].start();
		}
		
		for(int i=0; i<dretve.length; i++) {
			dretve[i].join();
		}
		System.out.println("Trenutna vrijednost brojaca je: "+brojac+".");
	}

	/**
	 * Razred koji predstavlja posao koji obavlja nasa dretva.
	 * Konkretno, radi se o uvecavanju zajednicke varijable zadani
	 * broj puta, uz uporabu sinkronizacije.
	 * 
	 * @author marcupic
	 */
	public static class PosaoDretve implements Runnable {
		/**
		 * koliko puta treba uvecati brojac za jedan
		 */
		private int brojUvecavanja;
		/**
		 * Sinkronizacijski objekt. 
		 */
		private Mutex mutex;
		
		/**
		 * Stvaranje novog posla sa zadanim brojem uvecavanja.
		 * 
		 * @param brojUvecavanja broj uvecavanja brojaca za jedan
		 * @param mutex sinkronizacijski objekt
		 */
		public PosaoDretve(int brojUvecavanja, Mutex mutex) {
			this.brojUvecavanja = brojUvecavanja;
			this.mutex = mutex;
		}

		public void run() {
			try {
				obaviPosao();
			} catch (InterruptedException ignroable) {}
		}

		/**
		 * Uvecavanje zajednickog brojaca uz definiranje kriticnog
		 * odsjecka.
		 * @throws InterruptedException 
		 */
		private void obaviPosao() throws InterruptedException {
			for(int i = 0; i < brojUvecavanja; i++) {
				mutex.udi();
				brojac = brojac + 1;
				mutex.izadi();
			}
		}
		
	}
}

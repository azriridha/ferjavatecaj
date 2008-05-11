package hr.fer.zemris.java.tecaj_7;

/**
 * Ilustracija problema koji nastaju pri paralelnom uvecavanju
 * iste varijable.
 * 
 * @author marcupic
 */
public class ParalelnoUvecavanje {

	/**
	 * Brojac koji uvecavamo.
	 */
	private static volatile long brojac = 0;
	/**
	 * Konstanta koja odreduje koliko dretvi zelimo pokrenuti.
	 */
	private static final int ZELJENI_BROJ_DRETVI = 5;
	
	/**
	 * Pokretanje programa.
	 * 
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Trenutna vrijednost brojaca je: "+brojac+".");

		PosaoDretve posao = new PosaoDretve(1000000);
		
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
	 * broj puta, bez uporabe ikakve sinkronizacije.
	 * 
	 * @author marcupic
	 */
	public static class PosaoDretve implements Runnable {
		/**
		 * koliko puta treba uvecati brojac za jedan
		 */
		private int brojUvecavanja;
		
		/**
		 * Stvaranje novog posla sa zadanim brojem uvecavanja.
		 * 
		 * @param brojUvecavanja broj uvecavanja brojaca za jedan
		 */
		public PosaoDretve(int brojUvecavanja) {
			this.brojUvecavanja = brojUvecavanja;
		}

		public void run() {
			obaviPosao();
		}

		/**
		 * Uvecavanje zajednickog brojaca.
		 */
		private void obaviPosao() {
			for(int i = 0; i < brojUvecavanja; i++) {
				brojac = brojac + 1;
			}
		}
		
	}
}

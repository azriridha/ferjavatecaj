package hr.fer.zemris.java.tecaj_7;

/**
 * Ilustracija pokretanja nove dretve.
 *  
 * @author marcupic
 */
public class PokretanjeDretve {

	/**
	 * Brojac koji nova dretva treba povecati.
	 */
	private static volatile int brojac = 0;
	
	/**
	 * Pokretanje programa.
	 * 
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Trenutna vrijednost brojaca je: "+brojac+".");

		PosaoDretve posao = new PosaoDretve(500000000);
		Thread thread = new Thread(posao, "radnik");

		System.out.println("Pokreæem novu dretvu...");
		thread.start();
		
		System.out.println("Èekam da dretva završi...");
		Thread.yield();
//		thread.join();
		System.out.println("Dretva je završila...");
		System.out.println("Trenutna vrijednost brojaca je: "+brojac+".");
	}

	/**
	 * Privatni razred koji predstavlja posao dretve. Konkretno, ovdje se radi
	 * o uvecavanju zajednickog brojaca.
	 *  
	 * @author marcupic
	 */
	public static class PosaoDretve implements Runnable {
		/**
		 * Za koliko treba povecati brojac
		 */
		private int brojUvecavanja;
		
		/**
		 * Stvaranje novog posla uz zadani broj uvecavanja.
		 * 
		 * @param brojUvecavanja broj uvecavanja za jedan
		 */
		public PosaoDretve(int brojUvecavanja) {
			this.brojUvecavanja = brojUvecavanja;
		}

		public void run() {
			System.out.println("Zapoèinje izvoðenje posla...");
			obaviPosao();
			System.out.println("Posao je gotov...");
		}

		/**
		 * Uvecavanje zajednicke varijable.
		 */
		private void obaviPosao() {
			System.out.println("Obavljam posao...");
			for(int i = 0; i < brojUvecavanja; i++) {
				brojac = brojac + 1;
			}
		}
		
	}
}

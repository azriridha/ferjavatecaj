package hr.fer.zemris.java.tecaj_2.vjezba;

/**
 * @author marcupic
 * @version 1.0
 * 
 * Pravokutnik je dio ravnine omeden okomitim i vodoravnim pravcem koji prolazi
 * kroz gornji lijevi ugao, a rasprostiranje desno i dolje odredeno je sirinom i
 * visinom.
 * 
 * Napomena: ovakva definicija onemogucava rad s pravokutnicima koji su u
 * prostoru zarotirani za neki kut koji nije visekratnik od 90 stupnjeva.
 */
public class Pravokutnik extends GeometrijskiLik {
	/** X koordinata gornjeg lijevog vrha. */
	private int vrhX;

	/** Y koordinata gornjeg lijevog vrha. */
	private int vrhY;

	/** Sirina pravokutnika. */
	private int sirina;

	/** Visina pravokutnika. */
	private int visina;

	/**
	 * Konstruktor pravokutnika.
	 */
	public Pravokutnik(int vrhX, int vrhY, int sirina, int visina) {
		super("Pravokutnik"); // Poziv konstruktora od g. lika
		this.vrhX = vrhX;
		this.vrhY = vrhY;
		this.sirina = sirina;
		this.visina = visina;
	}

	/**
	 * Zasticeni konstruktor pravokutnika. Sluzi kao potpora za razrede koji
	 * nasljeduju ovaj razred i zele definirati svoje vlastito ime.
	 */
	protected Pravokutnik(String ime, int vrhX, int vrhY, int sirina, int visina) {
		super(ime); // Poziv konstruktora od g. lika
		this.vrhX = vrhX;
		this.vrhY = vrhY;
		this.sirina = sirina;
		this.visina = visina;
	}

	/**
	 * Metoda za dohvat sirine pravokutnika.
	 * 
	 * @return sirinu pravokutnika
	 */
	public int getSirina() {
		return sirina;
	}

	/**
	 * Metoda za dohvat visine pravokutnika.
	 * 
	 * @return visinu pravokutnika.
	 */
	public int getVisina() {
		return visina;
	}

	/**
	 * Metoda za dohvat X koordinate gornjeg lijevog vrha pravokutnika.
	 * 
	 * @return x koordinatu vrha
	 */
	public int getVrhX() {
		return vrhX;
	}

	/**
	 * Metoda za dohvat Y koordinate gornjeg lijevog vrha pravokutnika.
	 * 
	 * @return y koordinatu vrha
	 */
	public int getVrhY() {
		return vrhY;
	}

	/**
	 * Izracun opsega pravokutnika; ova metoda prekriva istu metodu definiranu u
	 * razredu GeometrijskiLik.
	 */
	public double getOpseg() {
		return (double)(2 * sirina + 2 * visina);
	}

	/**
	 * Izracun povrsine pravokutnika; ova metoda prekriva istu metodu definiranu
	 * u razredu GeometrijskiLik.
	 */
	public double getPovrsina() {
		return sirina * visina;
	}

	/** 
	 * Metoda za usporedbu pravokutnika. 
	 * @return true ako su pravokutnici jednaki, inace false
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Pravokutnik))
			return false;
		Pravokutnik drugi = (Pravokutnik) obj;
		return vrhX == drugi.vrhX && vrhY == drugi.vrhY
				&& sirina == drugi.sirina && visina == drugi.visina;
	}

	/** 
	 * Vraca tekstualni prikaz ovog pravokutnika.
	 * @return tekstualni prikaz
	 */
	public String toString() {
		return super.toString() + "(" + vrhX + "," + vrhY + "," + sirina + ","
				+ visina + ")";
	}
	
	public static GeometrijskiLik fromStringArray(String[] params) throws IllegalArgumentException
	{
		int vrhX;
		int vrhY;
		int sirina;
		int visina;
		if (params.length != 4)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		try{
			vrhX = Integer.parseInt(params[0]);
			vrhY = Integer.parseInt(params[1]);
			sirina = Integer.parseInt(params[2]);
			visina = Integer.parseInt(params[3]);
		}catch (Exception e){
			throw new IllegalArgumentException("Nevaljali argumenti");
		}
		return new Pravokutnik(vrhX, vrhY, sirina, visina);
	}
	
	public boolean sadrziTocku(int x, int y)
	{
		if (x < vrhX || x > (vrhX + sirina) || y < vrhY || y > (vrhY + visina))
			return false;
		return true;
	}
	
	public void popuniLik(Slika slika)
	{
		for (int i = 0; i < sirina; i++)
			for (int j = 0; j < visina; j++)
				try{
					slika.upaliTocku(vrhX + i, vrhY + j);
				}catch (IndexOutOfBoundsException e){
					continue;
				}
	}
}

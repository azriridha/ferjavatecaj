package hr.fer.zemris.java.tecaj_2.vjezba;

/**
 * @author marcupic
 * @version 1.0
 * 
 * Kvadrat je pravokutnik cije su sve stranice iste duljine.
 */
public class Kvadrat extends Pravokutnik {

	/**
	 * Konstruktor kvadrata.
	 */
	public Kvadrat(int vrhX, int vrhY, int stranica) {
		super("Kvadrat", vrhX, vrhY, stranica, stranica);
	}

	/** 
	 * Metoda za usporedbu kvadrata. 
	 * @return true ako su kvadrati jednaki, inace false
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Kvadrat))
			return false;
		Kvadrat drugi = (Kvadrat) obj;
		return super.equals(drugi);
	}

	/** 
	 * Vraca tekstualni prikaz ovog kvadrata.
	 * @return tekstualni prikaz
	 */
	public String toString() {
		return "Kvadrat" + "(" + super.getVrhX() + "," + super.getVrhY() + ","
				+ super.getSirina() + ")";
	}
	
	public static GeometrijskiLik fromStringArray(String[] params) throws IllegalArgumentException
	{
		int vrhX;
		int vrhY;
		int sirina;
		if (params.length != 3)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		try{
			vrhX = Integer.parseInt(params[0]);
			vrhY = Integer.parseInt(params[1]);
			sirina = Integer.parseInt(params[2]);
		}catch (Exception e){
			throw new IllegalArgumentException("Nevaljali argumenti");
		}
		return new Kvadrat(vrhX, vrhY, sirina);
	}
}

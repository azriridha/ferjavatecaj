package hr.fer.zemris.java.tecaj_2.vjezba;


/**
 * @author marcupic
 * @version 1.0
 * 
 * Linija je jedan konkretan primjer geometrijskog lika. To je dio pravca
 * odreden pocetnom i zavrsnom tockom u 2D prostoru.
 */
public class Linija extends GeometrijskiLik {
	/** X koordinata pocetka linije. */
	private int x0;

	/** Y koordinata pocetka linije. */
	private int y0;

	/** X koordinata kraja linije. */
	private int x1;

	/** Y koordinata kraja linije. */
	private int y1;

	/**
	 * Konstruktor linije.
	 */
	public Linija(int x0, int y0, int x1, int y1) {
		super("Linija"); // Poziv konstruktora od geom. lika
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	/**
	 * Metoda za dohvat X koordinate pocetne tocke.
	 * 
	 * @return x koordinatu pocetne tocke
	 */
	public int getX0() {
		return x0;
	}

	/**
	 * Metoda za dohvat X koordinate zavrsne tocke.
	 * 
	 * @return x koordinatu zavrsne tocke
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Metoda za dohvat Y koordinate pocetne tocke.
	 * 
	 * @return y koordinatu pocetne tocke
	 */
	public int getY0() {
		return y0;
	}

	/**
	 * Metoda za dohvat Y koordinate zavrsne tocke.
	 * 
	 * @return y koordinatu zavrsne tocke
	 */
	public int getY1() {
		return y1;
	}

	/** 
	 * Metoda za usporedbu linija. 
	 * @return true ako su linije jednake, inace false
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Linija)) return false;
		Linija druga = (Linija) obj;
		return x0 == druga.x0 && y0 == druga.y0 && x1 == druga.x1
				&& y1 == druga.y1;
	}

	/** 
	 * Vraca tekstualni prikaz ove linije.
	 * @return tekstualni prikaz
	 */
	public String toString() {
		return super.toString() + "(" + x0 + "," + y0 + "," + x1 + "," + y1 + ")";
	}

	public static GeometrijskiLik fromStringArray(String[] params) throws IllegalArgumentException
	{
		int x0;
		int y0;
		int x1;
		int y1;
		if (params.length != 4)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		try{
			x0 = Integer.parseInt(params[0]);
			y0 = Integer.parseInt(params[1]);
			x1 = Integer.parseInt(params[2]);
			y1 = Integer.parseInt(params[3]);
		}catch (Exception e){
			throw new IllegalArgumentException("Nevaljali argumenti");
		}
		return new Linija(x0, y0, x1, y1);
	}
	
	@Override
	public boolean sadrziTocku(int x, int y){
		int x0 = this.x0;
		int y0 = this.y0;
		int x1 = this.x1;
		int y1 = this.y1;
		boolean steep = Math.abs(this.y1 - this.y0) > Math.abs(this.x1 - this.x0);
		if (steep)
		{
			x0 = this.y0;
			y0 = this.x0; 
			x1 = this.y1;
			y1 = this.x1;
			int temp = x;
			x = y;
			y = temp;
		}
		if (x0 > x1)
		{
			int temp = x0;
			x0 = x1;
			x1 = temp;
			temp = y0;
			y0 = y1;
			y1 = temp;
		}
		
		if (x < x0 || x > x1)
			return false;
		
		int deltaX = x1 - x0;
		int deltaY = Math.abs(y1 - y0);
		double error = 0;
		double deltaErr = (double)deltaY / (double)deltaX;
		int ystep;
		if (y0 < y1) 
			ystep = 1;
		else 
			ystep = -1;
		int j = y0;
		for (int i = x0; i < x; i++)
		{
			error += deltaErr;
			if (error >= 0.5)
			{
				j = j + ystep;
				error = error - 1;
			}
		}
		if (j == y)
			return true;
		else 
			return false;
	}
	
	public void popuniLik(Slika slika)
	{
		int x0 = this.x0;
		int y0 = this.y0;
		int x1 = this.x1;
		int y1 = this.y1;
		boolean steep = Math.abs(this.y1 - this.y0) > Math.abs(this.x1 - this.x0);
		if (steep)
		{
			x0 = this.y0;
			y0 = this.x0; 
			x1 = this.y1;
			y1 = this.x1;
		}
		if (x0 > x1)
		{
			int temp = x0;
			x0 = x1;
			x1 = temp;
			temp = y0;
			y0 = y1;
			y1 = temp;
		}
		
		int deltaX = x1 - x0;
		int deltaY = Math.abs(y1 - y0);
		double error = 0;
		double deltaErr = (double)deltaY / (double)deltaX;
		int ystep;
		if (y0 < y1) 
			ystep = 1;
		else 
			ystep = -1;
		int y = y0;
		for (int x = x0; x < x1; x++)
		{
			try {
				if (steep)
					slika.upaliTocku(y, x);
				else slika.upaliTocku(x, y);
			} catch (IndexOutOfBoundsException e) {
			} finally{
				error += deltaErr;
				if (error >= 0.5)
				{
					y = y + ystep;
					error = error - 1;
				}
			}
		}
	}
}

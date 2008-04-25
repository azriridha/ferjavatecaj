package hr.fer.zemris.java.tecaj_2.vjezba;

public class Elipsa extends GeometrijskiLik {
	
	private int centarX;
	private int centarY;
	private int radiusX;
	private int radiusY;
	
	
	public Elipsa(int centarX, int centarY, int radiusX, int radiusY) 
	{
		super("Elipsa");
		this.centarX = centarX;
		this.centarY = centarY;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}


	protected Elipsa(String ime, int centarX, int centarY, int radiusX, int radiusY)
	{
		super(ime);
		this.centarX = centarX;
		this.centarY = centarY;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}
	
	public int getCentarX() {
		return centarX;
	}


	public int getCentarY() {
		return centarY;
	}


	public int getRadiusX() {
		return radiusX;
	}


	public int getRadiusY() {
		return radiusY;
	}

	public double getOpseg()
	{
		return Math.PI * ( 3*(radiusX + radiusY) - Math.sqrt(
				(3*radiusX + radiusY)*(radiusX + 3*radiusY)));
	}
	
	public double getPovrsina()
	{
		return Math.PI * radiusX * radiusY;
	}

	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Elipsa))
			return false;
		Elipsa druga = (Elipsa) obj;
		return centarX == druga.centarX && centarY == druga.centarY
				&& radiusX == druga.radiusX && radiusY == druga.radiusY;
	}
	
	public String toString()
	{
		return "Elipsa(" + centarX + "," + centarY + "," + radiusX + "," + radiusY + ")";
	}
	
	public static GeometrijskiLik fromStringArray(String[] params) throws IllegalArgumentException
	{
		int centarX;
		int centarY;
		int radiusX;
		int radiusY;
		if (params.length != 4)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		try{
			centarX = Integer.parseInt(params[0]);
			centarY = Integer.parseInt(params[1]);
			radiusX = Integer.parseInt(params[2]);
			radiusY = Integer.parseInt(params[3]);
		}catch (Exception e){
			throw new IllegalArgumentException("Nevaljali argumenti");
		}
		return new Elipsa(centarX, centarY, radiusX, radiusY);
	}
	
	public boolean sadrziTocku(int x, int y)
	{
		if (x <= centarX - radiusX || x >= centarX + radiusX)
			return false;
		int rub = (int)Math.round(radiusY * Math.sqrt(1 - Math.pow(x - centarX, 2)/(Math.pow(radiusX, 2))));
		if (centarY + rub > y && centarY - rub < y)
			return true;
		else return false;
	}
	
	/*public void popuniLik(Slika slika)
	{
		int rub;
		for (int x = 0; x < radiusX; x++)
		{
			rub = (int)Math.round(radiusY * Math.sqrt(1 - Math.pow(x, 2)/(Math.pow(radiusX, 2))));
			for (int y = 0; y < rub; y++)
			{
				try{
					slika.upaliTocku(centarX + x,centarY + y);
				}catch(Exception e){}
				try{
					slika.upaliTocku(centarX + x,centarY - y);
				}catch(Exception e){}
				try{
					slika.upaliTocku(centarX - x,centarY + y);
				}catch(Exception e){}
				try{
					slika.upaliTocku(centarX - x,centarY - y);
				}catch(Exception e){}
			}	
		}
	}
*/}

package hr.fer.zemris.java.tecaj_2.vjezba;

public class Kruznica extends Elipsa {

	public Kruznica(int centarX, int centarY, int radius) {
		super("Kruznica", centarX, centarY, radius, radius);
	}

	public boolean equals(Object druga)
	{
		if (!(druga instanceof Kruznica))
			return false;
		return super.equals(druga);
	}
	
	public String toString()
	{
		return "Kruznica(" + super.getCentarX() + "," + super.getCentarY() + "," + super.getRadiusX() + ")";
	}
	
	public static GeometrijskiLik fromStringArray(String[] params) throws IllegalArgumentException
	{
		int centarX;
		int centarY;
		int radius;
		if (params.length != 3)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		try{
			centarX = Integer.parseInt(params[0]);
			centarY = Integer.parseInt(params[1]);
			radius =  Integer.parseInt(params[2]);
		}catch (Exception e){
			throw new IllegalArgumentException("Nevaljali argumenti");
		}
		return new Kruznica(centarX, centarY, radius);
	}
}

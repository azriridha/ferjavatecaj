package hr.fer.zemris.java.tecaj_4;

public class Zaposlenik implements Comparable<Zaposlenik> {
	private String sifra;
	private String prezime;
	private String ime;
	private double placa;
	
	public Zaposlenik(String sifra, String prezime, String ime) {
		super();
		this.sifra = sifra;
		this.prezime = prezime;
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public double getPlaca() {
		return placa;
	}

	public void setPlaca(double placa) {
		this.placa = placa;
	}

	public String getSifra() {
		return sifra;
	}
	
	@Override
	public String toString(){
		return "Zaposlenik: sifra=" + sifra +
			",prezime=" + prezime +
			",ime=" + ime +
			",placa=" + placa;
	}
	
	@Override
	public boolean equals(Object arg0){
		if (arg0==null) return false;
		System.out.println("Usporedjujem " + this + " i " + arg0);
		if (!(arg0 instanceof Zaposlenik)) return false;
		Zaposlenik drugi = (Zaposlenik)arg0;
		return Long.valueOf(sifra).equals(Long.valueOf(drugi.sifra))
				&& prezime.equals(drugi.prezime)
				&& ime.equals(drugi.ime);
		
	}

	@Override
	public int compareTo(Zaposlenik arg0) {
		if (arg0 == null) return 1;
		long res = Long.parseLong(sifra) - Long.parseLong(arg0.sifra);
		if (res < 0) return -1;
		else if (res > 0) return 1;
		else return 0;
	}
	
	@Override
	public int hashCode()
	{
		return Long.valueOf(sifra).hashCode()
			^ prezime.hashCode()
			^ ime.hashCode();
	}
}

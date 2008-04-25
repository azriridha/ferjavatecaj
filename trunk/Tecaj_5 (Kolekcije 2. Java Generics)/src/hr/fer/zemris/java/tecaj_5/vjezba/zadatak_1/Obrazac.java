package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

// TODO: Auto-generated Javadoc
/**
 * The Class Obrazac.
 */
public class Obrazac implements Comparable<Obrazac> {

	private Student student;
	private Grupa grupa;
	private String[] odgovori;
	private char[] status;
	private double[] bodovi;
	private double ukupniBodovi;
	
	
	/**
	 * Instancira novi obrazac. U obrascu se nalaze reference na studenta i grupu. Na temelju odgovora i
	 * bodovne politike racunaju se bodovi za pojedini zadatak i ukupni bodovi.
	 * 
	 * @param student student
	 * @param grupa grupa
	 * @param bodovnaPolitika bodovna politika
	 * @param odgovori odgovori
	 */
	public Obrazac(Student student, Grupa grupa, BodovnaPolitika bodovnaPolitika, String[] odgovori) {
		this.student = student;
		this.grupa = grupa;
		this.odgovori = new String[odgovori.length];
		System.arraycopy(odgovori, 0, this.odgovori, 0, odgovori.length);
		status = new char[grupa.getMaxBodovi().length];
		bodovi = new double[grupa.getMaxBodovi().length];
		ocijeni(bodovnaPolitika);
	}
	
	/**
	 * Ocjenjivanje studentovog ispita
	 * 
	 * @param bp bodovna politika
	 */
	private void ocijeni(BodovnaPolitika bp)
	{
		if (odgovori.length != grupa.getTocniOdgovori().length)
			throw new IllegalArgumentException("broj zadataka u grupi " + grupa.getOznakaGrupe() +
					"nije jednak broju odgovora studenta " + student);
		
		ukupniBodovi = 0;
		for (int i = 0; i < odgovori.length; i++)
		{
			if (odgovori[i].equals("BLANK"))
			{
				status[i] = '?';
				bodovi[i] = bp.getNeodgovoreno() * grupa.getMaxBodovi(i);
				ukupniBodovi += bodovi[i];
			}
			else if (odgovori[i].equals( Character.toString(grupa.getTocanOdgovor(i) )))
			{
				status[i] = 'T';
				bodovi[i] = bp.getTocno() * grupa.getMaxBodovi(i);
				ukupniBodovi += bodovi[i];
			}
			else 
			{
				status[i] = 'N';
				bodovi[i] = bp.getNetocno() * grupa.getMaxBodovi(i);
				ukupniBodovi += bodovi[i];	
			}
			
		}
	}

	/**
	 * Gets the odgovori.
	 * 
	 * @return the odgovori
	 */
	public String[] getOdgovori() {
		return odgovori;
	}
	
	/**
	 * Gets the odgovori.
	 * 
	 * @return the odgovori
	 */
	public String getOdgovor(int i) {
		return odgovori[i];
	}

	/**
	 * Sets the odgovori.
	 * 
	 * @param odgovori the new odgovori
	 */
	public void setOdgovori(String[] odgovori) {
		this.odgovori = odgovori;
	}

	/**
	 * Gets the student.
	 * 
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Gets the grupa.
	 * 
	 * @return the grupa
	 */
	public Grupa getGrupa() {
		return grupa;
	}

	/**
	 * Gets the status.
	 * 
	 * @param zadatak the zadatak
	 * 
	 * @return the status
	 */
	public char getStatus(int zadatak) {
		return status[zadatak];
	}
	
	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public char[] getStatus() {
		return status;
	}

	/**
	 * Gets the bodovi.
	 * 
	 * @param zadatak the zadatak
	 * 
	 * @return the bodovi
	 */
	public double getBodovi(int zadatak) {
		return bodovi[zadatak];
	}

	/**
	 * Gets the bodovi.
	 * 
	 * @return the bodovi
	 */
	public double[] getBodovi() {
		return bodovi;
	}
	
	/**
	 * Gets the ukupni bodovi.
	 * 
	 * @return the ukupni bodovi
	 */
	public double getUkupniBodovi() {
		return ukupniBodovi;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Obrazac arg0) {
		if (arg0 == null) return 1;
		double rez = ukupniBodovi - arg0.ukupniBodovi;
		if (rez > 0) return 1;
		else if (rez < 0) return -1;
		else return 0;
	}

}

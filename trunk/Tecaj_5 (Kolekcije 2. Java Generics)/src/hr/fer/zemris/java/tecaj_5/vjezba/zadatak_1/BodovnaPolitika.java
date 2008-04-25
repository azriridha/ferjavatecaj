package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

/**
 * The Class BodovnaPolitika.
 * @author HrvojeTorbasinovic
 */
public class BodovnaPolitika {

	private double tocno;
	private double netocno;
	private double neodgovoreno;
	
	/**
	 * Instancira novu bodovnu politiku.
	 * 
	 * @param tocno udio bodova koji se dobije ako je zadatak odgovoren tocno
	 * @param netocno udio bodova koji se dobije ako je zadatak odgovoren netocno
	 * @param neodgovoreno udio bodova koji se dobije ako zadatak nije odgovoren
	 * 
	 */
	

	public BodovnaPolitika(double tocno, double netocno, double neodgovoreno) {
		super();
		this.tocno = tocno;
		this.netocno = netocno;
		this.neodgovoreno = neodgovoreno;
	}

	/**
	 * Gets the tocno.
	 * 
	 * @return the tocno
	 */
	public double getTocno() {
		return tocno;
	}

	/**
	 * Gets the netocno.
	 * 
	 * @return the netocno
	 */
	public double getNetocno() {
		return netocno;
	}

	/**
	 * Gets the neodgovoreno.
	 * 
	 * @return the neodgovoreno
	 */
	public double getNeodgovoreno() {
		return neodgovoreno;
	}
	
}

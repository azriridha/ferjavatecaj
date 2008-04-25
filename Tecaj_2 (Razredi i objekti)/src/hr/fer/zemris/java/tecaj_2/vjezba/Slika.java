package hr.fer.zemris.java.tecaj_2.vjezba;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author marcupic
 * @version 1.0
 * 
 * Razred koji sluzi za simulaciju rada s crno-bijelom slikom, te njezinim prikazom
 * u tekstualnoj konzoli.
 */
public class Slika {

	/** Sirina slike. */
	private int sirina;

	/** Visina slike. */
	private int visina;

	/** Pikseli same slike. Vrijednost true oznacava ukljuceni piksel, a false iskljuceni. */
	private boolean[] mapa;

	/**
	 * Konstruktor slike koji stvara sliku zadanih dimenzija. Ako su sirina i/ili visina
	 * manji od 1, automatski ce biti postavljeni na 1.
	 * @param sirina sirina slike.
	 * @param visina visina slike.
	 */
	public Slika(int sirina, int visina) {
		super();
		if(sirina < 1) sirina = 1;
		if(visina < 1) visina = 1;
		this.sirina = sirina;
		this.visina = visina;
		mapa = new boolean[sirina * visina];
	}

	/**
	 * Metoda provjerava je li zadana tocka unutar podrucja slike. ako nije, baca iznimku.
	 * @param x x koordinata tocke koja se provjerava.
	 * @param y y koordinata tocke koja se provjerava.
	 * @throws IndexOutOfBoundsException ako se tocka ne nalazi unutar slike
	 */
	private void checkXY(int x, int y) throws IndexOutOfBoundsException {
		if(x<0||x>=sirina) throw new IndexOutOfBoundsException("X="+x+" is not in range [0,"+(sirina-1)+"]");
		if(y<0||y>=visina) throw new IndexOutOfBoundsException("Y="+y+" is not in range [0,"+(visina-1)+"]");
	}
	
	/**
	 * Metoda "ukljucuje" zadanu tocku na slici. Ako su koordinate tocke izvan slike, izaziva
	 * se iznimka.
	 * @param x x koordinata tocke koja se ukljucuje.
	 * @param y y koordinata tocke koja se ukljucuje.
	 * @throws IndexOutOfBoundsException ako se tocka ne nalazi unutar slike
	 */
	public void upaliTocku(int x, int y) throws IndexOutOfBoundsException {
		checkXY(x,y);
		mapa[y*sirina+x]=true;
	}

	/**
	 * Metoda "iskljucuje" zadanu tocku na slici. Ako su koordinate tocke izvan slike, izaziva
	 * se iznimka.
	 * @param x x koordinata tocke koja se iskljucuje.
	 * @param y y koordinata tocke koja se iskljucuje.
	 * @throws IndexOutOfBoundsException ako se tocka ne nalazi unutar slike
	 */
	public void ugasiTocku(int x, int y) throws IndexOutOfBoundsException {
		checkXY(x,y);
		mapa[y*sirina+x]=false;
	}

	/**
	 * Metoda "iskljucuje" sve piksele slike.
	 * @param x x koordinata tocke koja se ukljucuje.
	 * @param y y koordinata tocke koja se iskljucuje.
	 */
	public void izbrisiSliku() {
		Arrays.fill(mapa,false);
	}
	
	/**
	 * Metoda provjerava je li zadana tocka na slici ukljucena. 
	 * Ako su koordinate tocke izvan slike, izaziva se iznimka.
	 * @param x x koordinata tocke koja se ispituje.
	 * @param y y koordinata tocke koja se ispituje.
	 * @return true ako je tocka upaljena, false inace
	 * @throws IndexOutOfBoundsException ako se tocka ne nalazi unutar slike
	 */
	public boolean jeLiTockaUpaljena(int x, int y) throws IndexOutOfBoundsException {
		checkXY(x,y);
		return mapa[y*sirina+x];
	}

	/**
	 * Metoda crta tekstualni prikaz slike u datoteci koja se predaje kao argument 
	 * (to ce najcesce biti datoteka "stdout" - dakle sam zaslon).
	 * @param out datoteka gdje treba nacrtati sliku.
	 */
	public void nacrtajSliku(OutputStream out) {
		try {
			out.write('+');
			for(int i = 0; i < sirina; i++) out.write('-');
			out.write('+');
			out.write('\n');
			
			for(int y = 0; y < visina; y++) {
				out.write('|');
				for(int x = 0; x < sirina; x++) {
					if(mapa[y*sirina+x]==true) {
						out.write('*');
					} else {
						out.write('.');
					}
				}
				out.write('|');
				out.write('\n');
			}

			out.write('+');
			for(int i = 0; i < sirina; i++) out.write('-');
			out.write('+');
			out.write('\n');
		} catch (IOException e) {
			// Namjerno ignoriramo mogucnost iznimke...
		}
	}

	/**
	 * Metoda vraca sirinu same slike.
	 * @return sirina slike
	 */
	public int getSirina() {
		return sirina;
	}

	/**
	 * Metoda vraca visinu same slike.
	 * @return visina slike
	 */
	public int getVisina() {
		return visina;
	}

}

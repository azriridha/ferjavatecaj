package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_2;

public class PrimjerEkstremi {
	public static void main(String[] args) {
		Ekstremi<String> eks = new Ekstremi<String>(new String("AA"), new String("ZZ"));
		System.out.println("Minimum je: "+eks.getMin());
		System.out.println("Je li DD u tom rasponu? " + eks.isInRange("DD"));
	}
}
package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
public class Student implements Comparable<Student> {

	private String jmbag;
	private String prezime;
	private String ime;
	
	/**
	 * Instancira novog studenta.
	 * 
	 * @param jmbag the jmbag
	 * @param prezime the prezime
	 * @param ime the ime
	 */
	public Student(String jmbag, String prezime, String ime) {
		super();
		this.jmbag = jmbag;
		this.prezime = prezime;
		this.ime = ime;
	}
	
	
	
	/**
	 * Gets the jmbag.
	 * 
	 * @return the jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}
	
	/**
	 * Gets the prezime.
	 * 
	 * @return the prezime
	 */
	public String getPrezime() {
		return prezime;
	}
	
	/**
	 * Sets the prezime.
	 * 
	 * @param prezime the new prezime
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	/**
	 * Gets the ime.
	 * 
	 * @return the ime
	 */
	public String getIme() {
		return ime;
	}
	
	/**
	 * Sets the ime.
	 * 
	 * @param ime the new ime
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Student arg0) {
		if (arg0 == null) return 1;
		return jmbag.compareTo(arg0.jmbag);
	}
	
	@Override
	public boolean equals(Object arg0)
	{
		if (!(arg0 == null))
			return false;
		if (arg0 instanceof Student)
			return false;
		Student student = (Student)arg0;
		return jmbag.equals(student.jmbag);
	}
	
	@Override
	public int hashCode()
	{
		return jmbag.hashCode();
	}
}

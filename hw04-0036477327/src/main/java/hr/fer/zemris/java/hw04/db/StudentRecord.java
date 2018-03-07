package hr.fer.zemris.java.hw04.db;

/**
 * Razred koji predstavlja jednog studenta i enkapsulira njegove podatke.
 * @author Branko Sirotković
 *
 */
public class StudentRecord {

	/**
	 * JMBAG studenta.
	 */
	private String jmbag;
	
	/**
	 * Prezime studenta.
	 */
	private String lastName;
	
	/**
	 * Ime studenta.
	 */
	private String firstName;
	
	/**
	 * Konačna ocjena studenta.
	 */
	private int finalGrade;
	
	/**
	 * Konstruktor koji prima argumente koji inicijaliziraju varijable StudentRecorda.
	 * @param jmbag JMBAG studenta.
	 * @param lastName Prezime studenta.
	 * @param firstName Ime studenta.
	 * @param finalGrade Konačna ocjena studenta.
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
		if(jmbag == null || lastName == null || firstName == null){
			throw new IllegalArgumentException("Jedan od argumenata u konstruktoru StudentRecorda je null, a nesmije biti.");
		}
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}
	
	/**
	 * Getter za jmbag.
	 * @return jmbag.
	 */
	public String getJmbag() {
		return jmbag;
	}
	
	/**
	 * Getter za lastName.
	 * @return lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Getter za firstName.
	 * @return firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Getter za finalGrade.
	 * @return finalGrade.
	 */
	public int getFinalGrade() {
		return finalGrade;
	}
	
	
}

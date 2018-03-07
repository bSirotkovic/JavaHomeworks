package hr.fer.zemris.java.hw05.demo4;

/**
 * Razred koji definira jednog studenta i podatke o njemu.
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
	@SuppressWarnings("unused")
	private String lastName;

	/**
	 * Ime studenta.
	 */
	@SuppressWarnings("unused")
	private String firstName;

	/**
	 * Bodovi studenta na međuispitu.
	 */
	private double mi;

	/**
	 * Bodovi studenta na završnom ispitu.
	 */
	private double zi;

	/**
	 * Bodovi studenta na laboratorijskim vježbama.
	 */
	private double lab;

	/**
	 * Konačna ocjena studenta.
	 */
	private int grade;

	/**
	 * Konstruktor koji inicijalizira sve varijable StudentRecorda.
	 * @param studentInfo Polje sa podacima o studenta.
	 */
	public StudentRecord(String[] studentInfo) {
		if (studentInfo.length != 7) {
			throw new IllegalArgumentException("Predani su neispravno formatirani podaci o studentu.");
		}

		jmbag = studentInfo[0];
		lastName = studentInfo[1];
		firstName = studentInfo[2];
		mi = Double.valueOf(studentInfo[3]);
		zi = Double.valueOf(studentInfo[4]);
		lab = Double.valueOf(studentInfo[5]);
		grade = Integer.valueOf(studentInfo[6]);
	}

	/**
	 * Getter za ukupan broj bodova studenta.
	 * @return Ukupan broj bodova studenta.
	 */
	public double getTotalPoints() {
		return mi + zi + lab;
	}
	
	/**
	 * Getter za jmbag.
	 * @return jmbag.
	 */
	public String getJmbag(){
		return jmbag;
	}
	
	/**
	 * Getter za ocjenu studenta.
	 * @return Konačna ocjena.
	 */
	public int getGrade(){
		return grade;
	}
}

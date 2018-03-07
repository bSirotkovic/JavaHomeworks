package hr.fer.zemris.java.hw04.db;

/**
 * Sučelje koje ima jednu metodu. Ta metoda je namijenjena da provjeri je li određeni StudentRecord prihvatljiv.
 * @author Branko Sirotković
 *
 */
public interface IFilter {
	
	/**
	 * Metoda koja provjerava je li primljeni StudentRecord prihvatljiv.
	 * @param record StudentRecord koji se provjerava.
	 * @return boolean je li StudentRecord prihvaćen.
	 */
	public boolean accepts(StudentRecord record);
}

package hr.fer.zemris.java.hw04.db;

/**
 * Sučelje koje pruža jednu metodu. Metoda je namijenjena da usporedi dvije String vrijednosti.
 * @author User
 *
 */
public interface IComparisonOperator {

	/**
	 * Metoda koja uspoređuje dvije String vrijednosti.
	 * @param value1 Prvi String u usporedbi.
	 * @param value2 Drugi String u usporedbi.
	 * @return boolean koji ovisi o načinu usporedbe.
	 */
	public boolean satisfied(String value1, String value2);
}

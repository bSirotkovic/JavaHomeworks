package hr.fer.zemris.java.hw04.db;

/**
 * Sučelje koje ima jednu metodu. Metoda je namijenjena kao getter za jedan od atributa StudentRecord.
 * @author Branko Sirotković
 *
 */
public interface IFieldValueGetter {

	/**
	 * Metoda koja dohvaća jedan od atributa StudentRecord-a.
	 * @param record StudentRecord čiji atribut dohvaćamo.
	 * @return String vrijednost atributa.
	 */
	public String get(StudentRecord record);
}

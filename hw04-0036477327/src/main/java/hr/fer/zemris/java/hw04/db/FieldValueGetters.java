package hr.fer.zemris.java.hw04.db;

/**
 * Razred koji služi za dohvaćanje konstanti koje su implementacije IFieldValueGetter.
 * @author Branko Sirotković
 *
 */
public class FieldValueGetters {

	/**
	 * IFieldValueGetter koji dohvaća firstName atribut StudentRecorda.
	 */
	public static final IFieldValueGetter FIRST_NAME = record -> record.getFirstName();
	
	/**
	 * IFieldValueGetter koji dohvaća lastName atribut StudentRecorda.
	 */
	public static final IFieldValueGetter LAST_NAME = record -> record.getLastName();
	
	/**
	 * IFieldValueGetter koji dohvaća JMBAG atribut StudentRecorda.
	 */
	public static final IFieldValueGetter JMBAG = record -> record.getJmbag();
	
}

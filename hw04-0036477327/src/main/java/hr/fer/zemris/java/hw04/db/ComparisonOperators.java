package hr.fer.zemris.java.hw04.db;

/**
 * Razred koji se koristi za dobavljanje statičkih konstanti koje su implementacije 
 * sučelja IComparisonOperator.
 * @author Branko Sirotković
 *
 */
public class ComparisonOperators {

	/**
	 * IComparisonOperator koji provjerava je li prvi argument manji od drugoga.
	 */
	public static final IComparisonOperator LESS = 
			(str1, str2) -> { return str1.compareTo(str2) < 0; };
			
	/**
	 * IComparisonOperator koji provjerava je li prvi argument manji ili jednak drugome.
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = 
			(str1, str2) -> { return str1.compareTo(str2) < 1; };
			
	/**
	 * IComparisonOperator koji provjerava je li prvi argument veći od drugoga.
	 */
	public static final IComparisonOperator GREATER = 
			(str1, str2) -> { return str1.compareTo(str2) > 0; };
	
	/**
	 * IComparisonOperator koji provjerava je li prvi argument veći ili jednak drugome.		
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = 
			(str1, str2) -> { return str1.compareTo(str2) > -1; };
	
	/**
	 * IComparisonOperator koji provjerava je li prvi argument jednak drugome.
	 */
	public static final IComparisonOperator EQUALS = 
			(str1, str2) -> { return str1.equals(str2); };
	
	/**
	 * IComparisonOperator koji provjerava jesu li prvi i drugi argument različiti.
	 */
	public static final IComparisonOperator NOT_EQUALS = 
			(str1, str2) -> { return !str1.equals(str2); };
	
	/**
	 * IComparisonOperator koji provjerava je li prvi argument sličan drugom argumentu, tako da
	 * drugi argument sadrži * znak koji označava "bilo što".
	 */
	public static final IComparisonOperator LIKE = 
			(str1, str2) -> { str2 = str2.replaceAll("\\*", ".*"); return str1.matches(str2); };
}

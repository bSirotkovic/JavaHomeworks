package hr.fer.zemris.java.hw04.db;

/**
 * Razred koji predstavlja jedan uvjet usporedbe nad StudentRecordom. 
 * @author Branko Sirotković
 *
 */
public class ConditionalExpression {

	/**
	 * Varijabla koja govori koji atribut StudentRecorda se uspoređuje.
	 */
	private IFieldValueGetter fieldValue;
	
	/**
	 * Varijabla koja sprema vrijednost s kojom ćemo uspoređivati jedan atribut StudentRecorda.
	 */
	private String toCompare;
	
	/**
	 * Varijabla u kojoj je spremljen način uspoređivanja prethodne dvije varijable.
	 */
	private IComparisonOperator operator;
	
	/**
	 * 
	 * @param fieldValue Varijabla koja govori koji atribut StudentRecorda se uspoređuje.
	 * @param toCompare Varijabla koja sprema vrijednost s kojom ćemo uspoređivati jedan atribut StudentRecorda.
	 * @param operator Varijabla u kojoj je spremljen način uspoređivanja prethodne dvije varijable.
	 */
	public ConditionalExpression(IFieldValueGetter fieldValue, String toCompare, IComparisonOperator operator){
		if(fieldValue == null || toCompare == null || operator == null){
			throw new IllegalArgumentException("ConditionalExpression ne može primiti null vrijednost u konstruktoru.");
		}
		this.fieldValue = fieldValue;
		this.toCompare = toCompare;
		this.operator = operator;
	}

	/**
	 * Getter za fieldValue.
	 * @return fieldValue.
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldValue;
	}

	/**
	 * Getter za toCompare.
	 * @return toCompare.
	 */
	public String getStringLiteral() {
		return toCompare;
	}

	/**
	 * Getter za operator.
	 * @return operator.
	 */
	public IComparisonOperator getComparisonOperator() {
		return operator;
	}
	
	
}

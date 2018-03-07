package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja funkciju pronađenu u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementOperator extends Element{

	/**
	 * Simbol/operator.
	 */
	private String symbol;
	
	/**
	 * Konstruktor za varijablu symbol.
	 * @param symbol Simbol.
	 */
	public ElementOperator(String symbol){
		if(symbol == null){
			throw new IllegalArgumentException("Simbol ne može biti postavljen na null.");
		}
		this.symbol = symbol;
	}
	
	/**
	 * Getter za varijablu symbol.
	 * @return symbol.
	 */
	public String getName(){
		return symbol;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asText(){
		return symbol;
	}
}

package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja niz znakova pronađenih u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementString extends Element{

	/**
	 * Vrijednost Stringa.
	 */
	private String value;
	
	/**
	 * Konstruktor za varijablu value.
	 * @param value Vrijednost Stringa.
	 */
	public ElementString(String value){
		if(value == null){
			throw new IllegalArgumentException("Vrijednost Stringa ne može biti postavljena na null.");
		}
		this.value = value;
	}
	
	/**
	 * Getter za varijablu value.
	 * @return value.
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asText(){
		return value;
	}
}

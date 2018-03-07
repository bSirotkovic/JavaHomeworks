package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja cijeli broj pronađen u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementConstantInteger extends Element{

	/**
	 * Vrijednost cijelog broja.
	 */
	private int value;
	
	/**
	 * Konstruktor koji postavlja vrijednost varijable value.
	 * @param value double.
	 */
	public ElementConstantInteger(int value){
		this.value = value;
	}
	
	/**
	 * Getter za varijablu value.
	 * @return value.
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asText(){
		return String.valueOf(value);
	}
}

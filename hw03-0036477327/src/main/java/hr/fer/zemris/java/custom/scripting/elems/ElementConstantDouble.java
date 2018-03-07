package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja racionalni broj pronađen u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementConstantDouble extends Element{

	/**
	 * Vrijednost racionalnog broja.
	 */
	private double value;
	
	/**
	 * Konstruktor koji postavlja vrijednost varijable value.
	 * @param value double.
	 */
	public ElementConstantDouble(double value){
		this.value = value;
	}
	
	/**
	 * Getter za varijablu value.
	 * @return value.
	 */
	public double getValue(){
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

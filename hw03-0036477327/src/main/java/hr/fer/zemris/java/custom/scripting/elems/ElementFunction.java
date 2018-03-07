package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja funkciju pronađenu u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementFunction extends Element{

	/**
	 * Ime funkcije.
	 */
	private String name;
	
	/**
	 * Konstruktor za varijablu name.
	 * @param name Ime funkcije.
	 */
	public ElementFunction(String name){
		if(name == null){
			throw new IllegalArgumentException("Ime funkcije ne može biti postavljeno na null.");
		}
		this.name = name;
	}
	
	/**
	 * Getter za varijablu name.
	 * @return name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asText(){
		return name;
	}
}

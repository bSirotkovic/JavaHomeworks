package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji nasljeđuje razred Element. Predstavlja ime varijable pronađene u dokumentu koji se analizirao.
 * @author Branko Sirotković
 */
public class ElementVariable extends Element{

	/**
	 * Ime varijable.
	 */
	private String name;
	
	/**
	 * Konstruktor koji postavlja varijablu name.
	 * @param name
	 */
	public ElementVariable(String name){
		if(name == null){
			throw new IllegalArgumentException("Vrijednost Stringa ne može biti postavljena na null.");
		}
		this.name = name;
	}
	
	/**
	 * Getter za ime varijable.
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

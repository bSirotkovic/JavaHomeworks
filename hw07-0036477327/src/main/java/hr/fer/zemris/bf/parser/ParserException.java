package hr.fer.zemris.bf.parser;

/**
 * Razred koji predstavlja iznimku. Razred je izveden iz razred RuntimeExcpetion.
 * Ova iznimka se baca ukoliko dođe do neispravnog rada Parsera.
 * @author Branko
 *
 */
public class ParserException extends RuntimeException{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor koji sprema tekst iznimke koji će se prenijeti korisniku.
	 * @param s Poruka o iznimci.
	 */
	public ParserException(String s){
		super(s);
	}
}

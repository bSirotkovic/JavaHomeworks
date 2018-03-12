package hr.fer.zemris.bf.lexer;

/**
 * Razred koji predstavlja iznimku, izveden je iz razred RuntimeException, a iznimka se baca
 * kada nastane iznimke prilikom rada leksičkog analizatora.
 * @author Branko
 *
 */
public class LexerException extends RuntimeException{

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor koji sprema poruku iznimke koja će se prikazati korisniku.
	 * @param text Poruka iznimke.
	 */
	public LexerException(String text){
		super(text);
	}
}

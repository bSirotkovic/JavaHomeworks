package hr.fer.zemris.java.hw03.prob1;

/**
 * Razred koji nasljeđuje Exception. Razred predstavlja iznimku koja nastaje prilikom rada
 * leksičkog analizatora "Lexer".
 * @author Branko Sirotković
 *
 */
public class LexerException extends RuntimeException{

	/**
	 * ID iznimke.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	public LexerException(){
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public LexerException(String message) {
		super(message);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public LexerException(Throwable cause){
		super(cause);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public LexerException(String message, Throwable cause){
		super(message, cause);
	}

}

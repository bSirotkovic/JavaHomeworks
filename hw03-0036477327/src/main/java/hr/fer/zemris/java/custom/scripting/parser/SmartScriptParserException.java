package hr.fer.zemris.java.custom.scripting.parser;

/**
 * Specijalna iznimka koja nastupa prilikom neispravnog rada razreda SmartScriptParser.
 * @author Branko Sirotković
 *
 */
public class SmartScriptParserException extends RuntimeException{

	/**
	 * Verzija iznimke.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	public SmartScriptParserException(){
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SmartScriptParserException(String message) {
		super(message);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SmartScriptParserException(Throwable cause){
		super(cause);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SmartScriptParserException(String message, Throwable cause){
		super(message, cause);
	}

}

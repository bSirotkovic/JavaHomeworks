package hr.fer.zemris.bf.lexer;

/**
 * Razred koji predstavlja token kojega leksički analizator vraća prilikom obrade ulaznog teksta.
 * @author Branko
 *
 */
public class Token {
	
	/**
	 * Tip tokena.
	 */
	private TokenType type;
	
	/**
	 * Vrijednost tokena.
	 */
	private Object value;

	/**
	 * Konstruktor koji inicijalizira obe varijable Tokena.
	 * @param tokenType Tip tokena.
	 * @param tokenValue Vrijednost tokena.
	 */
	public Token(TokenType tokenType, Object tokenValue){
		type = tokenType;
		value = tokenValue;
	}
	
	/**
	 * Getter za tip tokena.
	 * @return Tip tokena.
	 */
	public TokenType getTokenType(){
		return type;
	}
	
	/**
	 * Getter za vrijednost tokena.
	 * @return Vrijednost tokena.
	 */
	public Object getTokenValue(){
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		String string = String.format("Type: %s, Value: %s", type.toString(), value);
		if(value != null){
			string = string + ", Value is instance of: " + value.getClass().getName();
		}
		
		return string;
	}
}

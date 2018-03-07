package hr.fer.zemris.java.hw03.prob1;

/**
 * Razred koji predstavlja jedan token koji se izgrađuje prilikom leksičke analize u jezičnom procesoru.
 * Token može biti tipa TokenType i sadrži vrijednost u varijabli value.
 * @author Branko Sirotković
 */
public class Token {
	
	/**
	 * Tip tokena, jedna od predefiniranih vrijednosti u enumeraciji TokenType.
	 */
	TokenType type;
	
	/**
	 * Vrijednost tokena.
	 */
	Object value;

	/**
	 * Konstruktor tokena, prima dva argumenta: tip i vrijednost.
	 * @param type TokenType, tip tokena.
	 * @param value Vrijednost tokena.
	 */
	public Token(TokenType type, Object value) {
		if(type == null){
			throw new IllegalArgumentException("U konstruktor tokena je poslan TokenType null.");
		}
		this.type = type;
		this.value = value;
	}

	/**
	 * Geter za varijablu "value".
	 * @return value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Geter za varijablu "type".
	 * @return type.
	 */
	public TokenType getType() {
		return type;
	}
}

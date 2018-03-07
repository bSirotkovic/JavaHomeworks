package hr.fer.zemris.java.hw03.prob1;

/**
 * Enumeracija tipova tokena koje leksički analizator može generirati.
 * @author Branko Sirotković
 *
 */
public enum TokenType {
	/**
	 * Token koji označava kraj dokumenta koji se analizira.
	 */
	EOF,
	
	/**
	 * Token za niz slova, odnosno riječ u tekstu.
	 */
	WORD, 
	
	/**
	 * Token za niz znamenki, odnosno broj u tekstu. Broj mora moći stati u Long format.
	 */
	NUMBER, 
	
	/**
	 * Token za simbol.
	 */
	SYMBOL
}

package hr.fer.zemris.bf.lexer;

/**
 * Enumeracija koja predstavlja sve moguće tipove tokena.
 * @author Branko
 *
 */
public enum TokenType {

	/**
	 * End of file token.
	 */
	EOF, 
	
	/**
	 * Token za varijablu.
	 */
	VARIABLE, 
	
	/**
	 * Token za konstantu, 0, 1, true ili false.
	 */
	CONSTANT, 
	
	/**
	 * Token za operator, poput or, and, xor, itd.
	 */
	OPERATOR, 
	
	/**
	 * Token za otvorenu zagradu, (.
	 */
	OPEN_BRACKET,
	
	/**
	 * Token za zatvorenu zagradu, ).
	 */
	CLOSED_BRACKET
}

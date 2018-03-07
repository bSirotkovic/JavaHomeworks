package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Enumeracija svih vrsta tokena koje pametni leksički analizator može generirati.
 * @author Branko Sirotković
 *
 */
public enum SmartTokenType {

	/**
	 * Token za kraj dokumenta koji se analizira.
	 */
	EOF,
	
	/**
	 * Token za ime funkcije. U dokumentu započinje sa '@'.
	 */
	FUNCTION,
	
	/**
	 * Token za sav tekst izvan tagova.
	 */
	TEXT,
	
	/**
	 * Token za ime varijable. Ime varijable započinje sa slovom, te ga slijedi bilo koji broj znakova/znamenki ili '_'
	 */
	VAR,
	
	/**
	 * Token za cijeli broj.
	 */
	INTEGER,
	
	/**
	 * Token za racionalni broj.
	 */
	DOUBLE,
	
	/**
	 * Token za operator. Podržani operatori su prikazani u konstantni SUPPORTED_OPERATORS u razredu SmartScriptLexer.
	 */
	OPERATOR,
	
	/**
	 * Token za ime taga '='. Taj posebni tag označava EchoNode.
	 */
	EQUALS,
	
	/**
	 * Token za String.
	 */
	STRING,
	
	/**
	 * Token za znakove '{$' i '$}' koji uzrokuju promijenu stanja leksičkog analizatora.
	 */
	SWITCH
}

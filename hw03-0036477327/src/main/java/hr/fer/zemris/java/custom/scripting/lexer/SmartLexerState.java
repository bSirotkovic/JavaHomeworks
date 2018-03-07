package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Enumeracija svih stanja u kojima se može nalaziti leksički analizator.
 * @author Branko Sirotković
 */
public enum SmartLexerState {

	/**
	 * Osnovno stanje pametnog leksičkog analizatora, kada razred sve stavlja u token Text.
	 * Dopuštene su dvije escape sekvence a to su '\\' i '\{'.
	 */
	BASIC,
	
	/**
	 * Stanje kada leksički analizator prolazi kroz jedan tag. Početak i kraj taga su definirani sa '{$' i '$}'
	 * Unutar taga mogu se generirati svi tokeni iz SmartTokenType osim TEXT.
	 */
	TAG
}

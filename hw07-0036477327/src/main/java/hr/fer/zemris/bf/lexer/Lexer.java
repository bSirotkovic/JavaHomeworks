package hr.fer.zemris.bf.lexer;

/**
 * Razred koji predstavlja lekički analizator.
 *
 * @author Branko
 */
public class Lexer {

	/**
	 * Tekst koji se leksički analizira.
	 */
	private char[] text;

	/**
	 * Trenutni index u tekstu.
	 */
	private int currentIndex;

	/**
	 * Trenutni token.
	 */
	private Token currentToken;

	/**
	 * Javni konstruktor kojemu se predaje String koji će se analizirati.
	 *
	 * @param expression
	 */
	public Lexer(String expression) {
		if (expression == null) {
			throw new IllegalArgumentException("Predan je null String konstruktoru lexera.");
		}

		text = expression.toCharArray();
	}

	public Token nextToken() {
		currentToken = nextTokenHelper();
		return currentToken;
	}

	/**
	 * Metoda koja vraća sljedeći token.
	 *
	 * @return Sljedeći token.
	 */
	public Token nextTokenHelper() {
		if (currentIndex > text.length) {
			throw new LexerException("Poziva se nextToken metoda lexera iako je lexer gotov s obradom teksta.");
		}

		while (currentIndex < text.length && Character.isWhitespace(text[currentIndex])) {
			currentIndex++;
		}

		if (currentIndex == text.length) {
			currentIndex++;
			return new Token(TokenType.EOF, null);
		}

		if (Character.isLetter(text[currentIndex])) {
			String identificator = parseIdentificator();
			return validTokenForIdentificator(identificator);
		}

		if (Character.isDigit(text[currentIndex])) {
			String numericals = parseNumericals();
			return validTokenForNumericals(numericals);
		}

		currentIndex++; //Povećavamo ukoliko se izvrši jedan od narednih 5 IF-ova. Ako ne, kompenziramo.
		if (text[currentIndex - 1] == '(') {
			return new Token(TokenType.OPEN_BRACKET, '(');
		}
		if (text[currentIndex - 1] == ')') {
			return new Token(TokenType.CLOSED_BRACKET, ')');
		}
		if (text[currentIndex - 1] == '*') {
			return new Token(TokenType.OPERATOR, "and");
		}
		if (text[currentIndex - 1] == '+') {
			return new Token(TokenType.OPERATOR, "or");
		}
		if (text[currentIndex - 1] == '!') {
			return new Token(TokenType.OPERATOR, "not");
		}
		currentIndex--; //Kompenzacija.

		if (currentIndex + 2 < text.length && String.copyValueOf(text, currentIndex, 3).equals(":+:")) {
			currentIndex += 3;
			return new Token(TokenType.OPERATOR, "xor");
		}

		throw new LexerException("Tekst kojeg lexer obrađuje sadrži nešto što se ne podržava: " + text[currentIndex]);
	}

	/**
	 * Metoda koja vraća odgovarajući token za jedan identifikator.
	 *
	 * @param identificator String koji počinje slovom, a nastavlja slovom, brojkom ili podvlakom.
	 * @return Ispravni token.
	 */
	private Token validTokenForIdentificator(String identificator) {

		switch (identificator.toLowerCase()) {
			case "and":
				return new Token(TokenType.OPERATOR, "and");

			case "or":
				return new Token(TokenType.OPERATOR, "or");

			case "xor":
				return new Token(TokenType.OPERATOR, "xor");

			case "not":
				return new Token(TokenType.OPERATOR, "not");

			case "true":
				return new Token(TokenType.CONSTANT, true);

			case "false":
				return new Token(TokenType.CONSTANT, false);

			default:
				return new Token(TokenType.VARIABLE, identificator.toUpperCase());
		}
	}

	/**
	 * Metoda koja vraća token za odgovarajući numerički string.
	 *
	 * @param numericals String koji se sastoji samo od znamenki.
	 * @return Ispravni token.
	 */
	private Token validTokenForNumericals(String numericals) {

		switch (numericals.toLowerCase()) {
			case "0":
				return new Token(TokenType.CONSTANT, false);

			case "1":
				return new Token(TokenType.CONSTANT, true);

			default:
				throw new LexerException("Lexer podržava nizove znamenki koji su jednaki samo 0 ili samo 1.");
		}
	}

	/**
	 * Metoda koja pasira numerički string te ga vraća.
	 *
	 * @return Numerički String.
	 */
	private String parseNumericals() {
		StringBuilder sb = new StringBuilder(); //Metoda je opširnija nego je potrebno, ali za eventualno nadogradnju.
		while (currentIndex < text.length && Character.isDigit(text[currentIndex])) {
			sb.append(text[currentIndex]);
			currentIndex++;
		}
		return sb.toString();
	}

	/**
	 * Metoda koja parsira identifikator te ga vraća.
	 *
	 * @return Identifikator.
	 */
	private String parseIdentificator() {
		StringBuilder sb = new StringBuilder(); //Metoda je opširnija nego je potrebno, ali za eventualno nadogradnju.
		while (currentIndex < text.length &&
				(Character.isDigit(text[currentIndex]) ||
						Character.isLetter(text[currentIndex]) ||
						text[currentIndex] == '_')) {

			sb.append(text[currentIndex]);
			currentIndex++;
		}
		return sb.toString();
	}

	public Token peekToken() {
		int currIdx = currentIndex;
		Token t;
		if (currIdx >= text.length) {
			t = new Token(TokenType.EOF, null);
		} else {
			t = nextTokenHelper();
		}
		currentIndex = currIdx;
		return t;
	}
}

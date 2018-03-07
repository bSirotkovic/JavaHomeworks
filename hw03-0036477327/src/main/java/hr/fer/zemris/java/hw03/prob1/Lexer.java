package hr.fer.zemris.java.hw03.prob1;

/**
 * Razred koji predstavlja primitivni leksički analizator. Lexer vraća parsira njemu predani tekst da pozivom 
 * odgovarajućih metoda vraća Tokene. Vrste mogućih tokena te njihova pravila definirana su u enumeraciji TokenType.
 * @author Branko Sirotković
 *
 */
public class Lexer {
	
	/**
	 * Podaci koji se leksički analiziraju.
	 */
	private char[] data;
	
	/**
	 * Trenutni (zadnje obrađeni) token.
	 */
	private Token token;
	
	/**
	 * Index prvog neobrađenog znaka u data.
	 */
	private int currentIndex;
	
	/**
	 * Trenutno stanje Lexera. Moguća stanja i njihove karakteristike su opisane u enumeraciji LexerState
	 */
	private LexerState state;
	
	/**
	 * Simbol na koji kada se naiđe Lexer mijenja state.
	 */
	private static final char SWITCH_STATES_SYMBOL = '#';

	/**
	 * Pretvara primljeni tekst u char array i njime inicijalizira data.
	 * @param text Tekst koji se želi leksički analizirati.
	 */
	public Lexer(String text) {
		if(text == null){
			throw new IllegalArgumentException("Lexer nije primio referencu na tekst.");
		}
		
		data = text.toCharArray();
		token = null;
		currentIndex = 0;
		state = LexerState.BASIC;
	}
	
	/**
	 * Setter metoda za varijablu state.
	 * @param state Stanje na koje želimo postaviti Lexer.
	 */
	public void setState(LexerState state){
		if(state == null){
			throw new IllegalArgumentException("Neispravan pokušaj setanja varijable state na null.");
		}
		this.state = state;
	}

	/**
	 * Metoda koja parsira data te vraća sljedeći token, te varijablu token postavlja na taj isti token.
	 * @return Sljedeći (prvi neobrađeni) token.
	 */
	public Token nextToken() {
		if(token != null && token.getType() == TokenType.EOF){
			throw new LexerException("Zahtjevao se sljedeći token, ali je Lexer već došao do kraja ulaznog teksta.");
		}
		
		if(currentIndex == data.length){
			return token = new Token(TokenType.EOF, null);
		}
		
		if(Character.isWhitespace(data[currentIndex])){
			currentIndex++;
			return nextToken();
		}
		
		if(state == LexerState.BASIC && (Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\')){
			return token = new Token(TokenType.WORD, parseWord());
		}
		
		if(state == LexerState.BASIC && Character.isDigit(data[currentIndex])){
			return token = new Token(TokenType.NUMBER, parseNumber());
		}
		
		if(state == LexerState.BASIC){
			return token = new Token(TokenType.SYMBOL, data[currentIndex++]);
		}
		
		if(state == LexerState.EXTENDED && data[currentIndex] == SWITCH_STATES_SYMBOL){
			currentIndex++;
			return token = new Token(TokenType.SYMBOL, SWITCH_STATES_SYMBOL);
		}
		
		return token = new Token(TokenType.WORD, parseExtended());
	}

	/**
	 * Getter za token, ukoliko prvi token još nije dohvaćen, poziva se obrada prvog tokena.
	 * @return Trenutni token.
	 */
	public Token getToken() {
		if(token == null){
			nextToken();
		}
		
		return token;
	}
	
	/**
	 * Metoda za parsiranje nenegativnog broja koji će biti vrijednost Tokena tipa TokenType.NUMBER.
	 * @return Parsirani broj.
	 */
	private Long parseNumber() {
		StringBuilder sb = new StringBuilder();
		
		while(currentIndex != data.length && Character.isDigit(data[currentIndex])){
			sb.append(data[currentIndex]);
			currentIndex++;
		}
		
		try{
			return new Long(sb.toString());
		} catch (NumberFormatException ex){
			throw new LexerException("Broj koji lexer parsira: " + sb.toString() + " je prevelik kako bi bio zapisan u long formatu.");
		}
	}
	
	/**
	 * Metoda za parsiranje riječi koja će biti vrijednost Tokena tipa TokenType.WORD.
	 * @return parsirani String.
	 */
	private String parseWord() {
		StringBuilder sb = new StringBuilder();
		while(currentIndex != data.length){
			
			if(data[currentIndex] == '\\'){
				sb.append(parseEscape());
			} else if(Character.isLetter(data[currentIndex])) {
				sb.append(data[currentIndex]);
				currentIndex++;
			} else {
				break;
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Metoda koja parsira data nakon što se naiđe na znak '\\'. Znak koji je ispravno escapan biti će dio Tokena tipa 
	 * TokenType.WORD.
	 * @return Znak koji je escapan. Taj char može biti i slovo ili znamenka, inače se baca LexerException.
	 */
	private char parseEscape(){
		if(currentIndex + 1 != data.length && (data[currentIndex+1] == '\\' || Character.isDigit(data[currentIndex+1]))){
			currentIndex += 2;
			return data[currentIndex - 1];
		}
		
		throw new LexerException("Neispravan pokušaj escapanja na poziciji: " + currentIndex);		
	}

	/**
	 * Metoda koja parsira data nakon što Lexer uđe u LexerState.EXTENDED stanje.
	 * @return String koji će biti vrijednost tokena tipa TokenType.WORD.
	 */
	private String parseExtended(){
		StringBuilder sb = new StringBuilder();
		while(currentIndex != data.length 
			  && !Character.isWhitespace(data[currentIndex]) 
			  && data[currentIndex] != SWITCH_STATES_SYMBOL){
			
			sb.append(data[currentIndex++]);
		}
		return sb.toString();
	}
}

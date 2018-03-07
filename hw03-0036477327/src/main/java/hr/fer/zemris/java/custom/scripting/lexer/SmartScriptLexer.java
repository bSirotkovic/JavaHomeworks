package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Razred koji provodi leksičku analizu nad ulaznim tekstualnim dokumentom
 * te svojim metodama vraća SmartToken objekte koji predstavljaju komade obrađenog
 * dokumenta.
 * @author Branko Sirotković
 *
 */
public class SmartScriptLexer {

	/**
	 * Podaci/dokument koji se analiziraju.
	 */
	private char[] data;
	
	/**
	 * Trenutni token, posljednji obrađeni.
	 */
	private SmartToken token;
	
	/**
	 * Indeks prvog neobrađenog tokena.
	 */
	private int currentIndex;
	
	/**
	 * Stanje leksičkog analizatora.
	 */
	private SmartLexerState state;
	
	/**
	 * Znak s kojim započinje funkcija u dokumentu.
	 */
	private final static char FUNCTION_SYMBOL = '@';
	
	/**
	 * Operatori koji se smiju naći unutar taga.
	 */
	private final static char[] SUPPORTED_OPERATORS = {'+', '-', '*', '/', '^'};
	
	/**
	 * Specifično ime taga koje nije u obliku imena varijable.
	 */
	private final static char SPECIAL_TAG_NAME = '=';
	
	/**
	 * Oznaka nakon koje se dokument treba obrađivati u TAG stanju.
	 */
	private final static String ENTER_TAG = "{$";
	
	/**
	 * Oznaka nakon koje se dokument treba obrađivati u BASIC stanju.
	 */
	private final static String EXIT_TAG = "$}";
	
	
	/**
	 * Konstruktor koji prima tekstualni dokument. Dokument koji je tipa String ne smije biti null.
	 * U tom slučaju baca IllegalArgumentException.
	 * @param document Dokument koji se leksički analizira.
	 */
	public SmartScriptLexer(String document) {
		if(document == null){
			throw new IllegalArgumentException("Leksički analizator je primio null document.");
		}
		
		data = document.toCharArray();
		state = SmartLexerState.BASIC;
		currentIndex = 0;
	}
	
	/**
	 * Metoda zaslužna za obradu sljedečeg tokena. Vraća isti taj token. Metoda sama mijenja 
	 * stanja leksičkog analizatora kada je potrebno.
	 * @return Novo obrađeni token.
	 */
	public SmartToken nextToken(){
		if(token != null && token.getType() == SmartTokenType.EOF){
			throw new SmartScriptParserException("Zahtjevao se sljedeći token, ali je Lexer već došao do kraja ulaznog teksta.");
		}
		
		if(currentIndex == data.length){
			return token = new SmartToken(SmartTokenType.EOF, null);
		}
		
		if(state == SmartLexerState.BASIC){ //Iako smo u prvom primjeru stanja mijenjali van lexera. Prirodnije je da se to obavlja automatski.
			
			if(checkForStateSwitchToTag()){
				currentIndex += 2;
				state = SmartLexerState.TAG;
				return token = new SmartToken(SmartTokenType.SWITCH, ENTER_TAG);
			}
			
			return token = parseText();
		}
		
		//Nadalje sve slučajevi kada je state = SmartLexerState.TAG
		if(checkForStateSwitchToBasic()){
			currentIndex += 2;
			state = SmartLexerState.BASIC;
			return token = new SmartToken(SmartTokenType.SWITCH, EXIT_TAG);
		}
		
		if(Character.isWhitespace(data[currentIndex])){
			currentIndex++;
			return token = nextToken();
		}
		
		if(Character.isLetter(data[currentIndex])){
			return token = new SmartToken(SmartTokenType.VAR, parseVarOrFunName());
		}
		
		if(Character.isDigit(data[currentIndex])){
			return token = parseNumber(false);
		}
		
		if(data[currentIndex] == '-'){
			if(currentIndex != data.length - 1 && Character.isDigit(data[currentIndex + 1])){
				currentIndex++;
				return token = parseNumber(true);
			}
			
			currentIndex++;
			return token = new SmartToken(SmartTokenType.OPERATOR, '-');
		}
		
		if(checkForOperator()){
			return token = new SmartToken(SmartTokenType.OPERATOR, data[currentIndex++]); 
		}
		
		if(data[currentIndex] == SPECIAL_TAG_NAME){
			currentIndex++;
			return token = new SmartToken(SmartTokenType.EQUALS, '=');
		}
		
		if(data[currentIndex] == FUNCTION_SYMBOL){
			currentIndex++;
			if(!Character.isLetter(data[currentIndex]) || currentIndex == data.length){
				throw new SmartScriptParserException("Neispravan naziv funkcije na poziciji: " + currentIndex);
			}
			String name = FUNCTION_SYMBOL + parseVarOrFunName();
			return token = new SmartToken(SmartTokenType.FUNCTION, name);
		}
		
		
		if(data[currentIndex] == '\"'){
			return token = parseString();
		}
		
		throw new SmartScriptParserException("Znak: " + data[currentIndex] + " nije prikladan znak i ovaj leksički analizator ga ne podržava.");	
	}
	
	/**
	 * Getter metoda za varijablu token. Ukoliko se metoda pozove prije obrade prvog tokena,
	 * ona poziva obradu prvog token.
	 * @return trenutni token.
	 */
	public SmartToken getToken(){
		if(token == null){
			nextToken();
		}
		
		return token;
	}
	
	/**
	 * Pomoćna metoda koja u tekstu provjerava treba li se upravo ući u TAG stanje.
	 * @return true ako je sljedeći element u tekstu "{$"
	 */
	private boolean checkForStateSwitchToTag(){
		return currentIndex != data.length - 1 
				&& data[currentIndex] == ENTER_TAG.charAt(0) 
				&& data[currentIndex + 1] == ENTER_TAG.charAt(1);
	}
	
	/**
	 * Pomoćna metoda koja u tekstu provjerava treba li se upravo ući u BASIC stanje.
	 * @return true ako je sljedeći element u tekstu "$}"
	 */
	private boolean checkForStateSwitchToBasic(){
		return currentIndex != data.length - 1 
				&& data[currentIndex] == EXIT_TAG.charAt(0) 
				&& data[currentIndex + 1] == EXIT_TAG.charAt(1);
	}

	/**
	 * Pomoćna metoda parseText() metodi koja provjerava nastupaju li pravilne escape sekvence prilikom rada te metode.
	 * @return true, ako u tekstu slijedi "\\" ili "\{".
	 */
	private boolean checkForBasicEscape(){
		if(data[currentIndex] == '\\'){
			if(currentIndex != data.length - 1 && (data[currentIndex + 1] == '\\' || data[currentIndex + 1] == '{')){
				return true;
			}
			
			throw new SmartScriptParserException("Neispravan pokušaj escapanja na poziciji " + currentIndex + " u dokumentu. ");
		}
		
		return false;
	}

	/**
	 * Pomoćna metoda koja provjerava je li sljedeći znak u tekstu jedan od podržanih operatora.sn
	 * @return true, ako je sljedeći znak jedan od podržanih operatora.
	 */
	private boolean checkForOperator(){
		for (char c : SUPPORTED_OPERATORS) {
			if(data[currentIndex] == c) return true;
		}
		
		return false;
	}
	
	/**
	 * Pomoćna metoda parseString() metodi koja provjerava nastupaju li pravilne escape sekvence prilikom rada te metode.
	 * @param sb StringBuilder u koji se nastavlja dodavati ispravno escape-an tekst.
	 * @return true, ako u tekstu slijedi "\\", "\"", "\r", "\n" ili "\t".
	 */
	private boolean checkForSpecialEscape(StringBuilder sb){
		if(data[currentIndex] != '\\') return false;
		
		if(currentIndex == data.length - 1){
			throw new SmartScriptParserException("Neispravan pokušaj escapanja na zadnjoj poziciji u dokumentu.");
		}
		
		currentIndex += 2; //duplicirani kod iz sva 4 naredna if-a.
		
		if((data[currentIndex - 1] == '\\' || data[currentIndex - 1] == '\"')){
			sb.append(data[currentIndex - 1]);
			return true;
		}
		
		if(data[currentIndex - 1] == 'n'){
			sb.append('\n');
			return true;
		}
		
		if(data[currentIndex - 1] == 't'){
			sb.append('\t');
			return true;
		}
		
		if(data[currentIndex - 1] == 'r'){
			sb.append('\r');
			return true;
		}
		
		throw new SmartScriptParserException("Neispravan pokušaj escapanja na poziciji " + currentIndex + " u dokumentu.");
	}
	
	/**
	 * Metoda koja parsira sav tekst van tagova, odnosno u BASIC stanju leksičkog analizatora.
	 * @return SmartToken tipa TEXT.
	 */
	private SmartToken parseText(){
		StringBuilder sb = new StringBuilder();
		while(currentIndex != data.length && !checkForStateSwitchToTag()){
			if(checkForBasicEscape()){
				currentIndex++;
			}
			
			sb.append(data[currentIndex]);
			currentIndex++;
		}
		
		return new SmartToken(SmartTokenType.TEXT, sb.toString());
	}
	
	/**
	 * Metoda koja parsira tekst unuta tagova po pravilima imena varijable ili funkcije, a to je da započinju
	 * sa slovom, a iza se mogu nalaziti slova, brojke ili '_'.
	 * @return String koji je obrađen kao ime varijable ili funkcije.
	 */
	private String parseVarOrFunName(){
		StringBuilder sb = new StringBuilder();
		
		while(currentIndex != data.length && 
				(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_'))
		{
			sb.append(data[currentIndex]);
			currentIndex++;
		}
		
		return sb.toString();
	}

	/**
	 * Metoda koja parsira cijele i racionalne brojeve unutar tagova.
	 * @param isNegative Parametar koji nam služi za pravilnu obradu broja u ovisnosti je li se nalazio znak '-' ispred njega.
	 * @return SmartToken tipa DECIMAL ili INTEGER.
	 */
	private SmartToken parseNumber(boolean isNegative){
		StringBuilder sb = new StringBuilder();
		boolean isDecimalNumber = false;
		
		while(currentIndex != data.length){
			if(Character.isDigit(data[currentIndex])){
				sb.append(data[currentIndex]);
				currentIndex++;
				continue;
			}
			
			if(data[currentIndex] == '.' && !isDecimalNumber){
				sb.append(data[currentIndex]);
				currentIndex++;
				isDecimalNumber = true;
				continue;
			}
			
			break;
		}
		
		try{
			int mul = isNegative ? -1 : 1;
			if(isDecimalNumber){
				return new SmartToken(SmartTokenType.DOUBLE, mul * Double.parseDouble(sb.toString()));
			}
			else{
				return new SmartToken(SmartTokenType.INTEGER, mul * Integer.parseInt(sb.toString()));
			}
			
		} catch (NumberFormatException ex){
			throw new SmartScriptParserException("Niz: " + sb.toString() + " se ne može pretvoriti u odgovarajući broj.");
		}	
	}

	/**
	 * Metoda koja parsira Stringove unutar tagova, odnosno onaj niz znakova koji započinje i završava sa ".
	 * @return SmartToken tipa String sa isparsiranim tekstom.
	 */
	private SmartToken parseString(){
		StringBuilder sb = new StringBuilder("\"");
		currentIndex++;
		
		while(data[currentIndex] != '\"'){
			if(checkForSpecialEscape(sb)) continue;
			
			sb.append(data[currentIndex]);
			currentIndex++;
			
			if(currentIndex == data.length){
				throw new SmartScriptParserException("String koji se obrađivao nije nikada zatvoren znakom \".");
			}
		}
		
		sb.append("\"");
		currentIndex++;
		return new SmartToken(SmartTokenType.STRING, sb.toString());
	}
	
}

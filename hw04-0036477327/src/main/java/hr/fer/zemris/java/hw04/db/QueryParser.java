package hr.fer.zemris.java.hw04.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Razred koji parsira String query koji prima u konstruktoru. Provjerava je li query
 * ispravno sastavljen, te ukoliko je od njega gradi listu List<ConditionalExpression>, inaće baca
 * RuntimeException sa odgovarajućom porukom. Također pruža dodatne metode za rad sa upitom.
 * @author Branko Sirotković
 *
 */
public class QueryParser {
	
	/**
	 * Podržani operatori u upitu koji se sastoje od jednog Charactera.
	 */
	private static final char[] SUPPORTED_OPERATORS_1CHAR = {'>', '<', '='};
	
	/**
	 * Podržani operatori u upitu koji se sastoje od dva Charactera.
	 */
	private static final String[] SUPPORTED_OPERATORS_2CHAR = {"<=", ">=", "!="};
	
	/**
	 * Lista ConditionalExpressiona koja je produkt rada parsera.
	 */
	private List<ConditionalExpression> conditions;
	
	/**
	 * Konstruktor koji prima query u obliku String te njime na odgovarajuć način puni listu conditions.
	 * @param query Upit koji parsiramo.
	 */
	public QueryParser(String query){
		QueryLexer lexer = new QueryLexer(query);
		conditions = new ArrayList<ConditionalExpression>();
		
		while(true){
			Token tokenField = lexer.nextToken();
			Token tokenOperator = lexer.nextToken();
			Token tokenValue = lexer.nextToken();
			
			if(tokenField.getType() != TokenType.FIELD){
				throw new RuntimeException("Nešto drugo se nalazi gdje se trebao nalaziti atribut studenta koji se pretražuje. Neispravna query naredba.");
			}
			
			if(tokenOperator.getType() != TokenType.OPERATOR){
				throw new RuntimeException("Nešto drugo se nalazi gdje se trebao nalaziti uvjetni operator. Neispravna query naredba.");
			}
			
			if(tokenValue.getType() != TokenType.STRING){
				throw new RuntimeException("Nešto drugo se nalazi gdje se trebala nalaziti vrijednost s kojom uspoređujemo atribut studenta. Neispravna query naredba.");
			}
			
			conditions.add(createConditionalExpression(tokenField, tokenOperator, tokenValue));
			
			Token temp = lexer.nextToken();
			if(temp.getType() == TokenType.EOF) return;
			
			if(temp.getType() == TokenType.AND) continue;
			
			throw new RuntimeException("Neispravna query naredba. Ili je trebala biti gotova, ili je trebalo pisati and.");
			
		}
	}
	
	/**
	 * Metoda koja pita je li primljeni query direktan. Odnosno provjerava li se JMBAG StudentRecorda
	 * i je li operator kojim se uspoređuje jednakost.
	 * @return boolean je li upit direktan.
	 */
	public boolean isDirectQuery(){
		return conditions.size() == 1 &&
				conditions.get(0).getFieldGetter() == FieldValueGetters.JMBAG &&
				conditions.get(0).getComparisonOperator() == ComparisonOperators.EQUALS;
	}
	
	/**
	 * Metoda koja vraća traženi JMBAG u slučaju da je upit direktan. Ukoliko se ova metoda pozove,
	 * a da upit nije bio direktan, metoda će baciti IllegalStateException.
	 * @return JMBAG iz ovog direktnog upita.
	 */
	String getQueriedJMBAG(){
		if(!isDirectQuery()){
			throw new IllegalStateException("Metoda getQueriedJMBAG se ne može pozivati, ako je se ne radi o direktnoj naredbi.");
		}
		
		return conditions.get(0).getStringLiteral();
	}
	
	/**
	 * Metoda koja vraća varijablu conditions.
	 * @return varijabla conditions.
	 */
	List<ConditionalExpression> getQuery(){
		return conditions;
	}
	
	/**
	 * Pomoćna metoda koja od tri tokena koja vraća lexer, ukoliko su ispravna gradi pravilnu trojku upita
	 * odnosno ConditionalExpression.
	 * @param tokenField token u kojem je vrijednost iz koje zaključujemo odgovarajući IFieldValueGetter.
	 * @param tokenOperator token u kojem je vrijednost iz koje zaključujemo odgovarajući IComparisonOperator.
	 * @param tokenValue token u kojem je spremljena vrijednost s kojom se uspoređuje.
	 * @return ConditionalExpression stvoren iz ulaznih argumenata.
	 */
	private ConditionalExpression createConditionalExpression(Token tokenField, Token tokenOperator, Token tokenValue) {
		IFieldValueGetter field;
		if(tokenField.getValue().equals("firstName")){
			field = FieldValueGetters.FIRST_NAME;
		} 
		else if(tokenField.getValue().equals("lastName")){
			field = FieldValueGetters.LAST_NAME;
		} 
		else{
			field = FieldValueGetters.JMBAG;
		}
		
		IComparisonOperator operator;
		if(tokenOperator.getValue().equals("<")){
			operator = ComparisonOperators.LESS;
		}
		else if(tokenOperator.getValue().equals(">")){
			operator = ComparisonOperators.GREATER;
		}
		else if(tokenOperator.getValue().equals("=")){
			operator = ComparisonOperators.EQUALS;
		}
		else if(tokenOperator.getValue().equals(">=")){
			operator = ComparisonOperators.GREATER_OR_EQUALS;
		}
		else if(tokenOperator.getValue().equals("<=")){
			operator = ComparisonOperators.LESS_OR_EQUALS;
		}
		else if(tokenOperator.getValue().equals("!=")){
			operator = ComparisonOperators.NOT_EQUALS;
		}
		else{
			operator = ComparisonOperators.LIKE;
		}
		
		if(operator == ComparisonOperators.LIKE){
			if(tokenValue.getValue().indexOf("*") != tokenValue.getValue().lastIndexOf("*")){
				throw new RuntimeException("Nakon LIKE slijedi literal sa više od jednog znaka *.");
			}
		} else {
			if(tokenValue.getValue().contains("*")){
				throw new RuntimeException("Koristi se wildcard nakon operatora koji nije LIKE.");
			}
		}
		
		return new ConditionalExpression(field, tokenValue.getValue(), operator);
	}

	/**
	 * Pomoćni razred QueryParseru. Njegov odgovarajući leksički analizator. Obrađuje query upit
	 * i vraća odgovarajuće tokene.
	 * @author Branko Sirotković
	 *
	 */
	private class QueryLexer{
		
		/**
		 * Podaci koje leksički analizator obrađuje.
		 */
		char[] data;
		
		/**
		 * Indeks prvog neobrađenog Charactera.
		 */
		int currentIndex;
		
		/**
		 * Konstruktor koji prima naredbu koju će leksički analizator obrađivati.
		 * @param query Naredba koju će se obrađivati.
		 */
		public QueryLexer(String query){
			data = query.toCharArray();
		}
		
		/**
		 * Metoda koja vraća prvi sljedeći token.
		 * @return Sljedeći token.
		 */
		public Token nextToken(){
			if(currentIndex == data.length){
				return new Token(TokenType.EOF, null);
			}
			
			if(Character.isWhitespace(data[currentIndex])){
				currentIndex++;
				return nextToken();
			}
			
			if(Character.isLetter(data[currentIndex])){
				if(data[currentIndex] == 'f'){
					return parseVariable("firstName");
				}
				if(data[currentIndex] == 'l'){
					return parseVariable("lastName");
				}
				if(data[currentIndex] == 'j'){
					return parseVariable("jmbag");
				}
				if(data[currentIndex] == 'L'){
					return parseVariable("LIKE");
				}
				if(data[currentIndex] == 'A' || data[currentIndex] == 'a'){
					return parseVariable("and");
				}
				
				throw new RuntimeException("Neispravna query naredba, problem nastaje na poziciji: " + currentIndex);
			}
			
			if(data[currentIndex] == '\"'){
				return parseString();
			}
			
			return parseOperator();
		}

		/**
		 * Metoda koja parsira atribut odnosno varijablu StudentRecorda. Token kojeg vraća će biti tipa
		 * FIELD osim u posebnom slučaju kada je tipa AND koji se također obrađuje u ovoj metodi. Također
		 * jedna od povratnih vrijednosti može biti token tipa OPERATOR, ako je parsirana riječ LIKE
		 * @param string String kojeg pokušavamo matchati sa narednim elementima data polja.
		 * @return Token tipa FIELD, AND ili OPERATOR.
		 */
		private Token parseVariable(String string) {
			if(string.equals("and")){
				if(String.valueOf(data, currentIndex, 3).toLowerCase().equals(string)){
					currentIndex += 3;
					return new Token(TokenType.AND, "and");
				}
				
				throw new RuntimeException("Neispravna query naredba, neka varijabla započinje sa 'A', a nije AND.");
			}
			
			for(int i = 0, n = string.length(); i < n; i++){
				if(! (data[currentIndex + i] == string.charAt(i))){
					throw new RuntimeException("Neispravna query naredba. Nije niti jedan field od StudentRecord niti LIKE");
				}
			}
			
			TokenType type = TokenType.FIELD;
			if(string.equals("LIKE")){
				type = TokenType.OPERATOR;
			}
			
			currentIndex += string.length();
			return new Token(type, string);
		}

		/**
		 * Metoda koja parsira vrijednost s kojom će se određeni atribut StudentRecorda uspoređivati,
		 * odnosno ono unutar navodnika u upitu.
		 * @return Token tipa STRING.
		 */
		private Token parseString() {
			currentIndex++; //da se pomaknemo sa početnog navodnika.
			StringBuilder sb = new StringBuilder();
			while(data[currentIndex] != '\"'){
				sb.append(data[currentIndex++]);
			}
			
			currentIndex++; //da se pomaknemo sa zadnjeg navodnika.
			return new Token(TokenType.STRING, sb.toString());
		}

		/**
		 * Metoda koja parsira operator u upitu, pomoću konstanti SUPPORTED_OPERATORS.
		 * @return Token OPERATOR.
		 */
		private Token parseOperator() {
			
			for (String operator : SUPPORTED_OPERATORS_2CHAR) {
				if(currentIndex == data.length - 1) break;
				
				if(operator.equals(String.valueOf(data, currentIndex, 2))){
					currentIndex += 2;
					return new Token(TokenType.OPERATOR, String.valueOf(data, currentIndex-2, 2));
				}
			}
			
			for(char operator : SUPPORTED_OPERATORS_1CHAR){
				if(data[currentIndex] == operator){
					currentIndex++;
					return new Token(TokenType.OPERATOR, String.valueOf(operator));
				}
			}
			
			throw new RuntimeException("Neispravna query naredba. Na poziciji: " + currentIndex + " se nalazi nedopušteni znak.");
		}

	}
	
	/**
	 * Pomoćni razred koji predstavlja Token za QueryLexer.
	 * @author Branko Sirotković
	 *
	 */
	private class Token{
		
		/**
		 * Tip tokena.
		 */
		private TokenType type;
		
		/**
		 * Vrijednost tokena.
		 */
		private String value;
		
		/**
		 * Konstruktor koji prima dva argumenta s kojima inicijalizira dvije varijable razreda.
		 * @param type tip tokena.
		 * @param value vrijednost tokena.
		 */
		public Token(TokenType type, String value){
			this.type = type;
			this.value = value;
		}
		
		/**
		 * Getter za tip tokena.
		 * @return tip tokena.
		 */
		public TokenType getType(){
			return type;
		}
		
		/**
		 * Getter za vrijednost tokena.
		 * @return vrijednost tokena.
		 */
		public String getValue(){
			return value;
		}
	}
	
	/**
	 * Enumeracija za moguće tipova razreda Token.
	 * @author Branko Sirotković
	 *
	 */
	private enum TokenType{
		/**
		 * Tip tokena koji predstavlja jedan atribut StudentRecorda.
		 */
		FIELD,
		
		/**
		 * Tip tokena koji predstavlja ono unutar navodnika u upitu.
		 */
		STRING,
		
		/**
		 * Token za ključnu riječ and u upitu.
		 */
		AND,
		
		/**
		 * Token za operator u upitu.
		 */
		OPERATOR,
		
		/**
		 * Token za kraj upita.
		 */
		EOF
	}
}

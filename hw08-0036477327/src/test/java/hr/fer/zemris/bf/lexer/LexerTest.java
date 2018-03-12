package hr.fer.zemris.bf.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

public class LexerTest {
	
	private void compareTokenTypes(Lexer lexer, Token[] correctData) {
		int counter = 0;
		for(Token expected : correctData) {
			Token actual = lexer.nextToken();
			String msg = "Checking token "+counter + ":";
			assertEquals(msg, expected.getTokenType(), actual.getTokenType());
			assertEquals(msg, expected.getTokenValue(), actual.getTokenValue());
			counter++;
		}
	}

	@Test
	public void testNotNull() {
		Lexer lexer = new Lexer("");
		
		assertNotNull("Token was expected but null was returned.", lexer.nextToken());
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInput() {
		// must throw!
		new Lexer(null);
	}

	
	@Test
	public void testEmpty() {
		Lexer lexer = new Lexer("");
		
		assertEquals("Empty input must generate only EOF token.", TokenType.EOF, lexer.nextToken().getTokenType());
	}

	@Test(expected=LexerException.class)
	public void testRadAfterEOF() {
		Lexer lexer = new Lexer("");

		// will obtain EOF
		lexer.nextToken();
		// will throw!
		lexer.nextToken();
	}
	
	@Test
	public void testEverythingLegal() {

		Lexer lexer = new Lexer("a or b :+: 1 aNd c + false * Z xOr (true)");
			
		// We expect the following stream of tokens
		Token correctData[] = {
			new Token(TokenType.VARIABLE, "A"),
			new Token(TokenType.OPERATOR, "or"),
			new Token(TokenType.VARIABLE, "B"),
			new Token(TokenType.OPERATOR, "xor"),
			new Token(TokenType.CONSTANT, true),
			new Token(TokenType.OPERATOR, "and"),
			new Token(TokenType.VARIABLE, "C"),
			new Token(TokenType.OPERATOR, "or"),
			new Token(TokenType.CONSTANT, false),
			new Token(TokenType.OPERATOR, "and"),
			new Token(TokenType.VARIABLE, "Z"),
			new Token(TokenType.OPERATOR, "xor"),
			new Token(TokenType.OPEN_BRACKET, '('),
			new Token(TokenType.CONSTANT, true),
			new Token(TokenType.CLOSED_BRACKET, ')')
		};

		compareTokenTypes(lexer, correctData);
	}
	
	@Test
	public void testEverythingStupidButLegal() {

		Lexer lexer = new Lexer("(()) var var a+b 1var");
			
		// We expect the following stream of tokens
		Token correctData[] = {
				new Token(TokenType.OPEN_BRACKET, '('),
				new Token(TokenType.OPEN_BRACKET, '('),
				new Token(TokenType.CLOSED_BRACKET, ')'),
				new Token(TokenType.CLOSED_BRACKET, ')'),
				new Token(TokenType.VARIABLE, "VAR"),
				new Token(TokenType.VARIABLE, "VAR"),
				new Token(TokenType.VARIABLE, "A"),
				new Token(TokenType.OPERATOR, "or"),
				new Token(TokenType.VARIABLE, "B"),
				new Token(TokenType.CONSTANT, true),
				new Token(TokenType.VARIABLE, "VAR")
		};

		compareTokenTypes(lexer, correctData);
	}
	
	@Test(expected=LexerException.class)
	public void testFail1(){
		Lexer lexer = new Lexer("neispravni broj 12");
		while(lexer.nextToken().getTokenType() != TokenType.EOF){
			
		}
	}
	
	@Test(expected=LexerException.class)
	public void testFail2(){
		Lexer lexer = new Lexer("nepostojeci znak &");
		while(lexer.nextToken().getTokenType() != TokenType.EOF){
			
		}
	}

}
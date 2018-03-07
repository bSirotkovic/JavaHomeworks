package hr.fer.zemris.java.hw03.prob1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;
import hr.fer.zemris.java.custom.scripting.lexer.SmartToken;
import hr.fer.zemris.java.custom.scripting.lexer.SmartTokenType;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

public class LexerTest {

	private String loader(String filename) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename)) {
			byte[] buffer = new byte[1024];
			while (true) {
				int read = is.read(buffer);
				if (read < 1)
					break;
				bos.write(buffer, 0, read);
			}
			return new String(bos.toByteArray(), StandardCharsets.UTF_8);
		} catch (IOException ex) {
			return null;
		}
	}
	
	private void compareTokenTypes(SmartScriptLexer lexer, SmartToken[] correctData) {
		int counter = 0;
		for(SmartToken expected : correctData) {
			SmartToken actual = lexer.nextToken();
			String msg = "Checking token "+counter + ":";
			assertEquals(msg, expected.getType(), actual.getType());
			
			counter++;
		}
	}

	@Test
	public void testNotNull() {
		SmartScriptLexer lexer = new SmartScriptLexer("");
		
		assertNotNull("Token was expected but null was returned.", lexer.nextToken());
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInput() {
		// must throw!
		new SmartScriptLexer(null);
	}

	
	@Test
	public void testEmpty() {
		SmartScriptLexer lexer = new SmartScriptLexer("");
		
		assertEquals("Empty input must generate only EOF token.", SmartTokenType.EOF, lexer.nextToken().getType());
	}

	
	@Test
	public void testGetReturnsLastNext() {
		// Calling getToken once or several times after calling nextToken must return each time what nextToken returned...
		SmartScriptLexer lexer = new SmartScriptLexer("");
		
		SmartToken token = lexer.nextToken();
		assertEquals("getToken returned different token than nextToken.", token, lexer.getToken());
		assertEquals("getToken returned different token than nextToken.", token, lexer.getToken());
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

		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc2.txt"));
			
		// We expect the following stream of tokens
		SmartToken correctData[] = {
			new SmartToken(SmartTokenType.TEXT, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.TEXT, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.DOUBLE, ""),
			new SmartToken(SmartTokenType.INTEGER, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.TEXT, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.TEXT, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.EQUALS, ""),
			new SmartToken(SmartTokenType.STRING, ""),
			new SmartToken(SmartTokenType.INTEGER, ""),
			new SmartToken(SmartTokenType.DOUBLE, ""),
			new SmartToken(SmartTokenType.OPERATOR, ""),
			new SmartToken(SmartTokenType.OPERATOR, ""),
			new SmartToken(SmartTokenType.OPERATOR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.INTEGER, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.TEXT, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.VAR, ""),
			new SmartToken(SmartTokenType.SWITCH, ""),
			new SmartToken(SmartTokenType.TEXT, ""),
		};

		compareTokenTypes(lexer, correctData);
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc6() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc6.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc7() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc7.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc8() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc8.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc9() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc9.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc10() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc10.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc11() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc11.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ErrorDescribedInDoc12() {
		SmartScriptLexer lexer = new SmartScriptLexer(loader("doc12.txt"));

		while(lexer.nextToken().getType() != SmartTokenType.EOF);
	}

}

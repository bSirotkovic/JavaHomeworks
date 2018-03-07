package hr.fer.zemris.java.hw03.prob1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import hr.fer.zemris.java.hw03.SmartScriptTester;

public class ParserTest {

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
	
	//Escapanje radi, ali ovaj test nije primjenjiv na to, jer prilikom escapanja se gube znakovi koji kasnije utjeću na
	//ponovnu izgradnju tokena.
	@Test
	public void everythingLegalWithoutEscaping() {
		SmartScriptParser parser = new SmartScriptParser(loader("doc14.txt"));

		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = SmartScriptTester.createOriginalDocumentBody(document);
		SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
		
		DocumentNode document2 = parser2.getDocumentNode();
		String originalDocumentBody2 = SmartScriptTester.createOriginalDocumentBody(document2); 
		
		assertEquals(true, originalDocumentBody2.equals(originalDocumentBody));
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void wrongNumOfArgsInFor(){
		@SuppressWarnings("unused")
		SmartScriptParser parser = new SmartScriptParser(loader("doc3.txt"));
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void noEnd(){
		SmartScriptParser parser = new SmartScriptParser(loader("doc4.txt"));
		parser.getDocumentNode();
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void WrongVarNameInFor(){
		@SuppressWarnings("unused")
		SmartScriptParser parser = new SmartScriptParser(loader("doc13.txt"));
	}
	
	@Test(expected=SmartScriptParserException.class)
	public void noClosedTag(){
		@SuppressWarnings("unused")
		SmartScriptParser parser = new SmartScriptParser(loader("doc15.txt"));
	}

}

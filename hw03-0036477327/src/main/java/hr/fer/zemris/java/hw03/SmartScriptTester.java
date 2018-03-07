package hr.fer.zemris.java.hw03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Razred koji služi za učitavanje tekstualne datotke te provođenje parsiranja nad njom.
 * Parser gradi strukturu čvorova od teksta te ponovo gradi tekst iz strukture čvorova, te ga zatim
 * ispisuje. Lokacije datoteke koju želimo obraditi šalje se naredbenim retkom.
 * @author Branko Sirotković
 *
 */
public class SmartScriptTester {

	/**
	 * Metoda koja se izvršava pokretanjem programa.
	 * @param Argumenti naredbenog retka.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String docBody = new String(
				Files.readAllBytes(Paths.get(args[0])),
				StandardCharsets.UTF_8
				);
		
		SmartScriptParser parser = new SmartScriptParser(docBody);
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document!");
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("If this line ever executes, you have failed this class!");
			System.exit(-1);
		}
		
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = createOriginalDocumentBody(document);
		SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
		
		DocumentNode document2 = parser2.getDocumentNode();
		String originalDocumentBody2 = createOriginalDocumentBody(document2);
		System.out.println(originalDocumentBody); // should write something like
													// original
		// content of docBody//
		System.out.println(originalDocumentBody2.equals(originalDocumentBody));
	}

	/**
	 * Metoda koja gradi originalni tekst iz vrhovnog čvora Document Node.
	 * @param document Document Node originalnog teksta.
	 * @return String cijeli ulazni tekst.
	 */
	public static String createOriginalDocumentBody(DocumentNode document) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < document.numberOfChildren(); i++){
			
			Node child = document.getChild(i);
			
			if(child instanceof TextNode){
				sb.append(reconstructText((TextNode)child));
			} else if(child instanceof EchoNode){
				sb.append(reconstructEcho((EchoNode)child));
			} else if(child instanceof ForLoopNode){
				sb.append(reconstructFor((ForLoopNode)child));
			} else{
				throw new SmartScriptParserException("Neispravan ulazan tekst. Parser je prekinuo s radom.");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Metoda koja gradi originalni tekst koji se nalazio u ForLopeNode-u.
	 * @param node ForLoopNode.
	 * @return String Tekstualni oblik cijelog čvora.
	 */
	private static String reconstructFor(ForLoopNode node) {
		StringBuilder sb = new StringBuilder("{$ FOR ");
		sb.append(node.getVariable().asText() + " " + node.getStartExpression().asText() + " " + node.getEndExpression().asText() + " ");
		if(node.getStepExpression() != null){
			sb.append(node.getStepExpression().asText() + " ");
		}
		sb.append("$}");
		
		for(int i = 0, n = node.numberOfChildren(); i < n; i++){
			
			Node child = node.getChild(i);
			
			if(child instanceof TextNode){
				sb.append(reconstructText((TextNode)child));
			} else if(child instanceof EchoNode){
				sb.append(reconstructEcho((EchoNode)child));
			} else if(child instanceof ForLoopNode){
				sb.append(reconstructFor((ForLoopNode)child));
			} else{
				throw new SmartScriptParserException("Neispravan ulazan tekst. Parser je prekinuo s radom.");
			}
		}
		
		sb.append("{$ END $}");
		
		return sb.toString();
	}

	/**
	 * Metoda koja gradi originalni tekst koji se nalazio u TextNode-u.
	 * @param node TextNode.
	 * @return String Tekstualni oblik cijelog čvora.
	 */
	private static String reconstructText(TextNode node) {
		return node.getText();
	}

	/**
	 * Metoda koja gradi originalni tekst koji se nalazio u EchoNode-u.
	 * @param node EchoNode.
	 * @return String Tekstualni oblik cijelog čvora.
	 */
	private static String reconstructEcho(EchoNode node){
		StringBuilder sb = new StringBuilder("{$= ");
		for (Element elem : node.getElements() ) {
			sb.append(elem.asText() + " ");
		}
		sb.append("$}");
		return sb.toString();
	}
}

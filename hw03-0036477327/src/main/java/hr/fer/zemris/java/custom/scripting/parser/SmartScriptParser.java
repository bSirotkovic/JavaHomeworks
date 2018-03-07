package hr.fer.zemris.java.custom.scripting.parser;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementConstantDouble;
import hr.fer.zemris.java.custom.scripting.elems.ElementConstantInteger;
import hr.fer.zemris.java.custom.scripting.elems.ElementFunction;
import hr.fer.zemris.java.custom.scripting.elems.ElementOperator;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;
import hr.fer.zemris.java.custom.scripting.lexer.SmartToken;
import hr.fer.zemris.java.custom.scripting.lexer.SmartTokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;

/**
 * Razred koji parsira dokument i od njega čini stablo Node-ova odnosno strukturirani prikaz
 * tog dokumenta.
 * @author Branko Sirotković
 *
 */
public class SmartScriptParser {

	/**
	 * Stablo čvorova što predstavljaju dokument.
	 */
	private ObjectStack nodeTree;
	
	/**
	 * Konstanta koja označava ulazak u tag.
	 */
	private final static String ENTER_TOKEN = "{$";
	
	/**
	 * Konstanta koja označava izlazak iz taga.
	 */
	private final static String EXIT_TOKEN = "$}";
	
	/**
	 * Konstanta koja predstavlja ime FOR taga.
	 */
	private final static String FOR_TAG = "for";
	
	/**
	 * Konstanta koja predstaljva ime END taga.
	 */
	private final static String END_TAG = "end";
	
	
	/**
	 * Konstruktor koji prima tekstualni dokument koji želimo parsirati. Dokument ne smije biti null.
	 * Te pokreće samo parsiranje.
	 * @param document Tekstualni dokument za parsiranje.
	 */
	public SmartScriptParser(String document){
		if(document == null){
			throw new IllegalArgumentException("Parseru je poslan null dokument.");
		}
		
		SmartScriptLexer lexer = new SmartScriptLexer(document);
		nodeTree = new ObjectStack();
		nodeTree.push(new DocumentNode());
		
		try{
			parseDocument(lexer);
		} catch(EmptyStackException ex){
			throw new SmartScriptParserException("Neispravno otvaranje i zatvaranje FOR i END tagova u tekstu.", ex);
		} catch(Exception ex){
			throw new SmartScriptParserException("Nešto je pošlo po zlu, ovo se nebi smjelo dogoditi, ali da se osiguram od \"pada predmeta\".");
		}
	}

	/**
	 * Metoda koja zapravo parsira dokument, prima leksički analizator koji omogućava rastav dokumenta na tokene.
	 * @param lexer Leksički analizator.
	 */
	private void parseDocument(SmartScriptLexer lexer) {
		SmartToken token = lexer.nextToken();
		
		while(token.getType() != SmartTokenType.EOF){
			
			if(token.getType() == SmartTokenType.TEXT){
				addToTree(new TextNode((String) token.getValue()));
				token = lexer.nextToken();
				
			} else if (token.getType() == SmartTokenType.SWITCH && token.getValue().equals(ENTER_TOKEN)){
				token = lexer.nextToken();
				
				if(token.getType() == SmartTokenType.EQUALS){
					addToTree(parseEchoNode(lexer));
					
				} else if(token.getType() == SmartTokenType.VAR && FOR_TAG.equals(String.valueOf(token.getValue()).toLowerCase())){
					ForLoopNode forNode = parseForNode(lexer);
					addToTree(forNode);
					nodeTree.push(forNode);
					
				} else if(token.getType() == SmartTokenType.VAR && END_TAG.equals(String.valueOf(token.getValue()).toLowerCase())){
					parseEndTag(lexer);
					
				} else{
					throw new SmartScriptParserException("Neispravno ime taga: " + token.getValue());
				}
				
				token = lexer.nextToken();
				
			} else {
				throw new SmartScriptParserException("Neispravno konstruiran dokument koji se parsira. Parser je prekinuo s radom.");
			}		
		}
	}

	/**
	 * Pomoćna metoda koja parsira END tag.
	 * @param lexer Leksički analizator.
	 */
	private void parseEndTag(SmartScriptLexer lexer) {
		nodeTree.pop();
		SmartToken token = lexer.nextToken();
		if(token.getType() == SmartTokenType.SWITCH && String.valueOf(token.getValue()).toLowerCase().equals(EXIT_TOKEN)){
			return;
		}
		
		throw new SmartScriptParserException("Unutar END taga se nalazi ekstra token koji nebi smio biti tu.");
	}

	/**
	 * Metoda koja parsira sve elemente koji pripadaju FOR tagu.
	 * @param lexer Leksički analizator.
	 * @return ForLoopNode pripadajučeg FOR taga.
	 */
	private ForLoopNode parseForNode(SmartScriptLexer lexer) {
		ArrayIndexedCollection col = parseElementsInTag(lexer);
		
		if(col.size() != 3 && col.size() != 4){
			throw new SmartScriptParserException("Neispravan broj argumenata u FOR tagu.");
		}
		
		if(!(col.get(0) instanceof ElementVariable)){
			throw new SmartScriptParserException("Prvi argument nakon FOR unutar FOR taga nije ElementVariable.");
		}
		
		checkElementInForTag(col.get(1));
		checkElementInForTag(col.get(2));
		
		if(col.size() == 3){
			return new ForLoopNode((ElementVariable)col.get(0), (Element)col.get(1), (Element)col.get(2), null);
		}
		
		checkElementInForTag(col.get(3));
		
		return new ForLoopNode((ElementVariable)col.get(0), (Element)col.get(1), (Element)col.get(2), (Element)col.get(3));
	}

	/**
	 * Metoda koja parsira pripadajuće elemente '=' taga.
	 * @param lexer Leksički analizator.
	 * @return Odgovarajući EchoNode.
	 */
	private EchoNode parseEchoNode(SmartScriptLexer lexer) {
		Object[] array = parseElementsInTag(lexer).toArray();
		Element[] elements = new Element[array.length];
		
		for(int i = 0; i < array.length; i++){
			elements[i] = (Element) array[i];
		}
		
		return new EchoNode(elements);
	}
	
	/**
	 * Metoda koja parsira sve pripadne elemente osim imena, bilo kojeg taga.
	 * @param lexer Leksički analizator
	 * @return Kolekciju svih elemenata što su bili u tagu.
	 */
	private ArrayIndexedCollection parseElementsInTag(SmartScriptLexer lexer){
		ArrayIndexedCollection col = new ArrayIndexedCollection();
		SmartToken token = lexer.nextToken();
		while(token.getType() != SmartTokenType.SWITCH){
			col.add(getElement(token));
			token = lexer.nextToken();
		}
		
		return col;
	}
	
	/**
	 * Metoda koja dodaje čvor u stablo čvorova nodeTree.
	 * @param node Čvor koji se dodaje.
	 */
	private void addToTree(Node node){
		Node parent = (Node) nodeTree.peek();
		parent.addChild(node);
	}

	/**
	 * Metoda koja od tokena po njegovome tipu vraća adekvatni tip Elementa, odnosno dijete razreda Element.
	 * @param token Token kojega želimo enkapsulirati u Element.
	 * @return Element.
	 */
	private Element getElement(SmartToken token) {
		if(token.getType() == SmartTokenType.DOUBLE) return new ElementConstantDouble((double) token.getValue());
		if(token.getType() == SmartTokenType.INTEGER) return new ElementConstantInteger((int) token.getValue());
		if(token.getType() == SmartTokenType.FUNCTION) return new ElementFunction((String) token.getValue());
		if(token.getType() == SmartTokenType.OPERATOR) return new ElementOperator(String.valueOf(token.getValue()));
		if(token.getType() == SmartTokenType.STRING) return new ElementString((String) token.getValue());
		if(token.getType() == SmartTokenType.VAR) return new ElementVariable((String) token.getValue());
		
		throw new SmartScriptParserException("Unutar taga se nalazi neispravan token vrijednosti: " + token.getType());
	}

	/**
	 * Metoda koja vraća DocumentNode is stabla nodeTree.
	 * @return DocumentNode.
	 */
	public DocumentNode getDocumentNode() {
		Node docNode = (Node) nodeTree.pop();
		if(!(docNode instanceof DocumentNode)){
			throw new SmartScriptParserException("Neuspjelo parsiranje, jedan od tagova koji je trebao biti zatvoren, nije zatvoren.");
		}
		
		return (DocumentNode) docNode;
	}
	
	/**
	 * Metoda koja provjerava jesu li elementi u FOR tagu, ispravnog tipa.
	 * @param elem
	 */
	private void checkElementInForTag(Object elem){
		if(elem instanceof ElementConstantInteger
			|| elem instanceof ElementConstantDouble
			|| elem instanceof ElementString
			|| elem instanceof ElementVariable)
			return;
		
		throw new SmartScriptParserException("Argument nakon FOR unutar FOR taga nije Element tipa variable, number ili string.");
	}
}

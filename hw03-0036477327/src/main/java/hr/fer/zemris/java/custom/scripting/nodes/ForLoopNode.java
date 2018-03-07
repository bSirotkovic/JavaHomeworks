package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 * Razred koji predstavlja čvor za FOR petlju u tekstualnom dokumentu
 * koji se ide prikazati strukturirano čvorovima. Sve između FOR taga i END taga koji ga zatvara
 * se sprema kao dijete ForLoopNode-a.
 * @author Branko Sirotković
 *
 */
public class ForLoopNode extends Node{

	/**
	 * Prva varijablu unutar FOR taga.
	 */
	private ElementVariable variable;
	
	/**
	 * Prvi izraz unutar FOR taga.
	 */
	private Element startExpression;
	
	/**
	 * Drugi izraz unutar FOR taga.
	 */
	private Element endExpression;
	
	/**
	 * Treći i opcionalni izraz unutar FOR taga.
	 */
	private Element stepExpression;
	
	/**
	 * Konstruktor koji inicijalizira sve varijable ovog razreda. Svi primljeni parametri osim zadnjega, ne smiju biti null.
	 * @param var variable
	 * @param startE startExpression
	 * @param endE endExpression
	 * @param stepE stepExpression, smije biti null.
	 */
	public ForLoopNode(ElementVariable var, Element startE, Element endE, Element stepE) {
		
		if(var == null || startE == null  || endE == null){
			throw new IllegalArgumentException("Jedan od primljenih parametara u konstruktoru ForLoopNode-a je bio null.");
		}
		
		variable = var;
		startExpression = startE;
		endExpression = endE;
		stepExpression = stepE;
	}
	
	/**
	 * Getter za varijablu variable.
	 * @return variable.
	 */
	public ElementVariable getVariable(){
		return variable;
	}
	
	/**
	 * Getter za varijablu startExpression.
	 * @return startExpression.
	 */
	public Element getStartExpression(){
		return startExpression;
	}
	
	/**
	 * Getter za varijablu endExpression.
	 * @return endExpression.
	 */
	public Element getEndExpression(){
		return endExpression;
	}
	
	/**
	 * Getter za varijablu stepExpression.
	 * @return stepExpression.
	 */
	public Element getStepExpression(){
		return stepExpression;
	}
}

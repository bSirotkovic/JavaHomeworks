package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;

/**
 * Razred koji predstavlja onaj čvor koji je u tekstualnom dokumentu prikazan kao tag čije je ime "=". 
 * @author Branko Sirotković
 *
 */
public class EchoNode extends Node{

	/**
	 * Svi elementi unutar tog taga, nakon imena '='.
	 */
	private Element[] elements;
	
	/**
	 * Konstruktor koji prima polje svih elemenata.
	 * @param elements
	 */
	public EchoNode(Element[] elements){
		this.elements = elements;
	}
	
	/**
	 * Getter za polje elemenata.
	 * @return elements.
	 */
	public Element[] getElements(){
		return elements;
	}
}

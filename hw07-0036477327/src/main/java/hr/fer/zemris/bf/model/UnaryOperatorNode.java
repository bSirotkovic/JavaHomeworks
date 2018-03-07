package hr.fer.zemris.bf.model;

import java.util.function.UnaryOperator;

/**
 * Razred koji predstavlja jedan čvor koji obuhvaća operator not u binarnom izrazu.
 * @author Branko
 *
 */
public class UnaryOperatorNode implements Node{

	/**
	 * Ime čvora.
	 */
	private String name;
	
	/**
	 * Čvor dijete, tj. čvor nad kojim djeluje not operator.
	 */
	private Node child;
	
	/**
	 * Operator koji zapravo djeluje na boolean vrijednost čvora dijeteta.
	 */
	private UnaryOperator<Boolean> operator;
	
	/**
	 * Javni konstruktor koji inicijalizira sve varijable čvora.
	 * @param name Ime čvora.
	 * @param child Dijete čvora.
	 * @param operator Operator čvora.
	 */
	public UnaryOperatorNode(String name, Node child, UnaryOperator<Boolean> operator){
		if(name == null || child == null){
			throw new IllegalArgumentException("Null argument je predan UnaryOperatorNode konstruktoru.");
		}
		
		this.name = name;
		this.child = child;
		this.operator = operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Getter za ime.
	 * @return Ime čvora.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter za čvor dijete.
	 * @return Node, čvor dijete.
	 */
	public Node getChild() {
		return child;
	}
	
	/**
	 * Getter za operator.
	 * @return operator.
	 */
	public UnaryOperator<Boolean> getOperator() {
		return operator;
	}
}

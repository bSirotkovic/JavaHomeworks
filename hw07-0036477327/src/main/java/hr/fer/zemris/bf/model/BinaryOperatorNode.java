package hr.fer.zemris.bf.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Čvor koji reprezentira operator nad dva argument u binarnom izrazu za koji 
 * se gradi stablo sastavljeno od čvorova.
 * @author Branko
 *
 */
public class BinaryOperatorNode implements Node{

	/**
	 * Ime čvora.
	 */
	private String name;
	
	/**
	 * Lista djece čvorova, odnosno čvorova nad kojima ovaj operator djeluje.
	 */
	private List<Node> children;
	
	/**
	 * Operator koji će se primjenjivati nad boolean vrijednostima djece.
	 */
	private BinaryOperator<Boolean> operator;
	
	/**
	 * Javni konstruktor koji inicijalizira sve varijable ovog čvora.
	 * @param name Ime čvora.
	 * @param children Lista djece čvorova.
	 * @param operator Operator čvora.
	 */
	public BinaryOperatorNode(String name, List<Node> children, BinaryOperator<Boolean>operator) {
		if(name == null || children == null){
			throw new IllegalArgumentException("Null argument je predan UnaryOperatorNode konstruktoru.");
		}
		
		this.name = name;
		this.children = new ArrayList<>(children);
		this.operator = operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Getter za ime čvora.
	 * @return Ime čvora.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter za djecu čvora.
	 * @return Lista djece čvorova.
	 */
	public List<Node> getChildren() {
		return children;
	}
	
	/**
	 * Getter za operator čvora.
	 * @return Operator čvora.
	 */
	public BinaryOperator<Boolean> getOperator() {
		return operator;
	}
}

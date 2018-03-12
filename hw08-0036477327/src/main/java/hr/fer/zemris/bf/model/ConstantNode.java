package hr.fer.zemris.bf.model;

/**
 * Razred koji predstavlja konkretnu implementaciju razreda Node.
 * Ovaj čvor obuhvaća konstantu u binarniom izrazu.
 * @author Branko
 *
 */
public class ConstantNode implements Node{

	/**
	 * Vrijednost konstante.
	 */
	private boolean value;
	
	/**
	 * Javni konstruktor.
	 * @param value Vrijednost na koju postavljamo value varijablu čvora.
	 */
	public ConstantNode(boolean value) {
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Getter za varijablu value.
	 * @return boolean value.
	 */
	public boolean getValue() {
		return value;
	}
}

package hr.fer.zemris.bf.model;

/**
 * Razred koji predstavlja čvor koji obuhvaća neku varijablu unutar binarnog izraza.
 * @author Branko
 *
 */
public class VariableNode implements Node{

	/**
	 * Ime varijable.
	 */
	private String name;
	
	/**
	 * Javni konstruktor koji namješta ime varijable.
	 * @param name Ime varijable.
	 */
	public VariableNode(String name) {
		if(name == null){
			throw new IllegalArgumentException("Null String je predan konstruktoru VariableNode.");
		}
		this.name = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Getter za ime.
	 * @return Ime varijable.
	 */
	public String getName() {
		return name;
	}
}

package hr.fer.zemris.bf.model;

/**
 * Sučelje koje definira metode posječivaća čvorova, odnosno
 * različitu metodu za svaki tip čvora koji se može posjetiti.
 * @author Branko
 *
 */
public interface NodeVisitor {

	/**
	 * Metoda koju će konkretni NodeVisitor pozvati kada želi posjetiti ConstantNode.
	 * @param node ConstanteNode koji se posjećuje.
	 */
	public void visit(ConstantNode node);
	
	/**
	 * Metoda koju će konkretni NodeVisitor pozvati kada želi posjetiti VariableNode.
	 * @param node VariableNode koji se posjećuje.
	 */
	public void visit(VariableNode node);
	
	/**
	 * Metoda koju će konkretni NodeVisitor pozvati kada želi posjetiti UnaryOperatorNode.
	 * @param node UnaryOperatorNode koji se posjećuje.
	 */
	public void visit(UnaryOperatorNode node);
	
	/**
	 * Metoda koju će konkretni NodeVisitor pozvati kada želi posjetiti BinaryOperatorNode.
	 * @param node BinaryOperatorNode koji se posjećuje.
	 */
	public void visit(BinaryOperatorNode node);
}

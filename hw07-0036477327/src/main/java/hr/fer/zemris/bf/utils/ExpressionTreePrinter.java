package hr.fer.zemris.bf.utils;

import hr.fer.zemris.bf.model.BinaryOperatorNode;
import hr.fer.zemris.bf.model.ConstantNode;
import hr.fer.zemris.bf.model.Node;
import hr.fer.zemris.bf.model.NodeVisitor;
import hr.fer.zemris.bf.model.UnaryOperatorNode;
import hr.fer.zemris.bf.model.VariableNode;

/**
 * Posjetitelj koji ispisuje stablo izraza po razinama.
 * @author Branko
 *
 */
public class ExpressionTreePrinter implements NodeVisitor{

	/**
	 * Integer u kojem se pamti razinu u kojoj se nalazimo u stablu.
	 */
	private int indentationLevel = 0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ConstantNode node) {
		printIndentation();
		System.out.println(node.getValue() ? 1 : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(VariableNode node) {
		printIndentation();
		System.out.println(node.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(UnaryOperatorNode node) {
		printIndentation();
		System.out.println(node.getName());
		indentationLevel += 2;
		visit(node.getChild());
		indentationLevel -= 2; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(BinaryOperatorNode node) {
		printIndentation();
		System.out.println(node.getName());
		
		indentationLevel += 2;
		for (Node child : node.getChildren()) {
			visit(child);
		}
		indentationLevel -= 2;
	}

	/**
	 * Pomoćna metoda koja ispisuje odgovarajuće razmake na svakoj razini stabla.
	 */
	private void printIndentation() {
		for(int i = 0; i < indentationLevel; i++){
			System.out.print(" ");
		}
	}

	/**
	 * Pomoćna metoda koja poziva odgovarajuću metodu visit ovog razreda u ovisnosti
	 * kojeg je tipa Node child.
	 * @param child Čvor dijete kojeg želimo posjetiti sljedećeg.
	 */
	private void visit(Node child) {
		if(child instanceof ConstantNode){
			visit((ConstantNode) child);
		}
		else if(child instanceof VariableNode){
			visit((VariableNode) child);
		}
		else if(child instanceof BinaryOperatorNode){
			visit((BinaryOperatorNode) child);
		}
		else if(child instanceof UnaryOperatorNode){
			visit((UnaryOperatorNode) child);
		}
	}

}

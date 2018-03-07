package hr.fer.zemris.bf.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import hr.fer.zemris.bf.model.BinaryOperatorNode;
import hr.fer.zemris.bf.model.ConstantNode;
import hr.fer.zemris.bf.model.Node;
import hr.fer.zemris.bf.model.NodeVisitor;
import hr.fer.zemris.bf.model.UnaryOperatorNode;
import hr.fer.zemris.bf.model.VariableNode;

/**
 * Konkretni posjetitelj odnosno implementacija razreda NodeVisitor.
 * Ovaj razred prilikom posjećivanja čvorvoa stabla traži i bilježi sve različite varijable
 * koje se javljaju u izrazu.
 * @author Branko
 *
 */
public class VariablesGetter implements NodeVisitor{

	/**
	 * Set varijabli koje su u izrazu.
	 */
	Set<String> variables;
	
	/**
	 * Javni konstruktor.
	 */
	public VariablesGetter() {
		variables = new TreeSet<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ConstantNode node) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(VariableNode node) {
		variables.add(node.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(UnaryOperatorNode node) {
		visit(node.getChild());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(BinaryOperatorNode node) {
		for (Node child : node.getChildren()) {
			visit(child);
		}
	}
	
	/**
	 * Pomoćna metoda koja poziva odgovarajuću visit metodu ovog razreda u ovisnosti
	 * kojeg je tipa Node child.
	 * @param Node child, sljedeći čvor kojeg želimo posjetiti.
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

	/**
	 * Metoda koja vraća sortiranu listu svih varijabli.
	 * @return Sortirana lista varijabli u izrazu.
	 */
	public List<String> getVariables() {
		return new ArrayList<>(variables);
	}

}

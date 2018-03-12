package hr.fer.zemris.bf.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import hr.fer.zemris.bf.model.BinaryOperatorNode;
import hr.fer.zemris.bf.model.ConstantNode;
import hr.fer.zemris.bf.model.Node;
import hr.fer.zemris.bf.model.NodeVisitor;
import hr.fer.zemris.bf.model.UnaryOperatorNode;
import hr.fer.zemris.bf.model.VariableNode;

/**
 * Posjetitelj odnosno konkretna implementacija razreda NodeVisitor.
 * Ovaj posjetitelj računa vrijednost cijel izraza koji se nalazi u stablu.
 * @author Branko
 *
 */
public class ExpressionEvaluator implements NodeVisitor{

	/**
	 * Konkretne vrijednosti true ili false svih varijabli.
	 */
	private boolean[] values;
	
	/**
	 * Mapa koja povezuje ime varijable (ključ) te poziciju unutar polja
	 * values gdje je spremljena vrijednost te varijable.
	 */
	private Map<String, Integer> positions;
	
	/**
	 * Stog koji se koristi prilikom evaluacije izraza.
	 */
	private Stack<Boolean> stack = new Stack<>();
	
	/**
	 * Konstruktor koji prima listu varijabli. 
	 * @param variables Lista varijabli u izrazu.
	 */
	public ExpressionEvaluator(List<String> variables) {
		if(variables == null){
			throw new IllegalArgumentException("Null lista je predana konstruktoru ExpressionEvaluatora.");
		}
		positions = new HashMap<>();
		for(int i = 0; i < variables.size(); i++){
			positions.put(variables.get(i), i);
		}
	}
	
	/**
	 * Metoda koja postavlja vrijednosti varijabli odnosno polje values.
	 * @param values Polje booleana koje predstavlja vrijednosti varijabli u izrazu.
	 */
	public void setValues(boolean[] values) {
		if(values == null || values.length != positions.size()){
			throw new IllegalArgumentException("Neispravno polje values je predano metodi setValues.");
		}
		this.values = values;
		start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ConstantNode node) {
		stack.push(node.getValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(VariableNode node) {
		Integer position = positions.get(node.getName());
		if(position == -1){
			throw new IllegalStateException("Evaluator nema podataka o varijabli koja se trenutno posjećivala.");
		}
		
		stack.push(values[position]);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(UnaryOperatorNode node) {
		visit(node.getChild());
		stack.push(node.getOperator().apply(stack.pop()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(BinaryOperatorNode node) {
		for (Node child : node.getChildren()) {
			visit(child);
		}
		
		boolean value = stack.pop();
		for(int i = 1, n = node.getChildren().size(); i < n; i++){
			value = node.getOperator().apply(value, stack.pop());
		}
		stack.push(value);
	}
	
	/**
	 * Metoda koja prazni stog.
	 */
	public void start() {
		stack = new Stack<>();
	}
	
	/**
	 * Metoda koja se poziva nakon što je završila evaluacija izraza.
	 * @return Vraća boolean rezultat cijelog izraza.
	 */
	public boolean getResult() {
		if(stack == null || stack.size() != 1){
			throw new IllegalStateException("Nešto je pošlo po zlu, na stogu ima više od jednog elementa.");
		}
		
		return stack.peek();
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

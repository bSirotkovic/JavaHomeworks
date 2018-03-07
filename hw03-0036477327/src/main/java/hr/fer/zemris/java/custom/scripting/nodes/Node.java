package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

/**
 * Razred koji predstavlja čvor u strukturiranom prikazu tekstualnog dokumenta.
 * @author Branko Sirotković
 *
 */
public class Node {

	/**
	 * Kolkecija čvorova djece ovog čvora.
	 */
	private ArrayIndexedCollection nodes;
	
	/**
	 * Metoda za dodavanje čvora djeteta. Ne može se dodati null Node.
	 * @param node Čvor koji se dodaje u kolekciju dijece ovoga čvora.
	 */
	public void addChild(Node node){
		if(node == null){
			throw new IllegalArgumentException("Ne može se dodati null čvor kao dijete čvora.");
		}
		if(nodes == null){
			nodes = new ArrayIndexedCollection();
		}
		
		nodes.add(node);
	}
	
	/**
	 * Metoda koja vraća broj djece čvora.
	 * @return Broj djece čvora.
	 */
	public int numberOfChildren(){
		if(nodes == null){
			return 0;
		}
		
		return nodes.size();
	}
	
	/**
	 * Metoda koja vraća dijete na indeksu primljenom kao parametar.
	 * @param index Indeks mora biti u rasponu [0, nodes.size). Inače nastupa IndexOutOfBoundsException.
	 * @return Dijete čvora na indeksu index.
	 */
	public Node getChild(int index){
		int size;
		
		if(nodes == null){
			size = 0;
		} else {
			size = nodes.size();
		}
		
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(index + " nije u dopuštenom rasponu indeksa.");
		}
		
		return (Node) nodes.get(index);
	}
}

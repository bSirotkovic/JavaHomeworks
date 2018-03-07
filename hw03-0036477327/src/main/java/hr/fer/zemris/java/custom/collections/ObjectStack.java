package hr.fer.zemris.java.custom.collections;

/**
 * Class that represents a stack for any objects. If you try to access an element
 * of an empty stack the methods will throw an EmptyStackException.
 * @author Branko Sirotković
 */
public class ObjectStack {

	/**
	 * An ArrayIndexedCollection that ObjectStack is using for actual element storage.
	 */
	private ArrayIndexedCollection array;
	
	/**
	 * Default constructor. Sets the size of the stack to 16.
	 */
	public ObjectStack(){
		array = new ArrayIndexedCollection();
	}
	
	/**
	 * Method which determines is the collection empty.
	 * @return Returns true if size of the collection is 0. False otherwise.
	 */
	public boolean isEmpty(){
		return array.isEmpty();
	}
	
	/**
	 * Method which should return the number of elements in the collection.
	 * @return Returns size of the collection.
	 */
	public int size(){
		return array.size();
	}
	
	/**
	 * Pushes given value on the stack.
	 * @param value Object to push. Null is not allowed.
	 */
	public void push(Object value){
		if(value == null){
			throw new IllegalArgumentException("Ne može se dodati null vrijednost u stog.");
		}
		array.add(value);
	}
	
	/**
	 * Returns last element placed on stack. If stack is empty, throws EmptyStackException.
	 * @return last element placed on stack.
	 */
	public Object peek(){
		if(array.size() == 0){
			throw new EmptyStackException("Pokušaj čitanja elementa sa stoga dok je stog prazan.");
		}
		return array.get(array.size()-1);
	}
	
	/**
	 * Removes last value pushed on stack from stack and returns it. If the stack is empty when
	 * method pop is called, the method throws EmptyStackException.
	 * @return last value pushed on stack.
	 */
	public Object pop(){
		if(array.size() == 0){
			throw new EmptyStackException("Pokušaj \"popanja\" elementa sa stoga dok je stog prazan.");
		}
		Object poppedObject = array.get(array.size()-1);
		array.remove(array.size()-1);
		return poppedObject;
	}
	
	/**
	 * Removes all elements from the collection.
	 */
	public void clear(){
		array.clear();
	}
}

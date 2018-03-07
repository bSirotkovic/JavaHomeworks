package hr.fer.zemris.java.custom.collections;

/**
 * Collection is a class designed to represent a Collection 
 * of some data. It has basic methods for that data manipulation.
 * Most of the methods are not implemented.
 * @author Branko Sirotković
 *
 */
public class Collection {

	/**
	 * Default constructor. Empty method.
	 */
	protected Collection(){
		
	}
	
	/**
	 * Method which determines is the collection empty.
	 * @return Returns true if size of the collection is 0. False otherwise.
	 */
	public boolean isEmpty(){
		if(this.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Method which should return the number of legal elements in the collection.
	 * Here implemented to always return 0.
	 * @return 0
	 */
	public int size(){
		return 0;
	}
	
	/**
	 * Adds the given object into this collection. Unimplemented method.
	 * @param value An object to add to the collection.
	 */
	public void add(Object value){
		
	}
	
	/**
	 * Method that checks is the given param already in the collection. 
	 * Here always returns false.
	 * @param value An object that should be checked is it in the collection.
	 * @return false.
	 */
	public boolean contains(Object value){
		return false;
	}
	
	/**
	 * Method that should remove an object from a collection if it's the same as the given param.
	 * Not implemented in this class, Always returns false.
	 * @param value An object that should be removed if in collection.
	 * @return false.
	 */
	public boolean remove(Object value){
		return false;
	}
	
	/**
	 * Returns the collection in a form of an object array.
	 * Method unimplemented and throws UnsupportedOperationException.
	 * @return nothing. Throws an exception.
	 * @throws UnsupportedOperationException.
	 */
	public Object[] toArray(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Method calls processor.process() for each element of this collection. 
	 * Method unimplemented.
	 * @param processor An instance of the Processor class that should call .process() method.
	 */
	public void forEach(Processor processor){
		
	}
	
	/**
	 * Method adds into itself all elements from given collection. The other collection remains unchanged.
	 * @param other Collection to be added to the original collection.
	 */
	public void addAll(Collection other){
		
		class LocalProcessor extends Processor{
			
			public void process(Object value){
				add(value);
			}
		}
		
		if(other == null){
			return;
		}
		
		LocalProcessor lp = new LocalProcessor();
		other.forEach(lp);
	}
	
	/**
	 * Removes all elements from this collection. Unimplemented.
	 */
	public void clear(){
		
	}
}

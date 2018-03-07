package hr.fer.zemris.java.custom.collections;

/**
 * Class that represents resizable array-backed collection of objects.
 * The collection isn't designed to hold null values and will throw an exception
 * if user tries to insert it.
 * @author Branko Sirotković
 */
public class ArrayIndexedCollection extends Collection{
	
	/**
	 * Default capacity of ArrayIndexedCollection.
	 */
	private static final int DEFAULT_CAPACITY = 16;
	/**
	 * Current size of collection (number of elements actually stored).
	 */
	private int size;
	
	/**
	 * Current capacity of allocated array of object references.
	 */
	private int capacity;
	
	/**
	 * An array of object references which length is determined by capacity variable.
	 */
	private Object[] elements;
	
	/**
	 * Default constructor, sets capacity of the collection to 16.
	 */
	public ArrayIndexedCollection(){
		this(null, DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor that sets capacity of the array collection to the given parameter.
	 * @param initialCapacity Represents wanted array size.
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		this(null, initialCapacity);
	}
	
	/**
	 * Constructor that receives a reference to another collection. All of the
	 * elements from the other collection are copied to this array and sets the
	 * capacity of the collection to the maximum between given collections size and 16.
	 * @param col Reference to a collection whos elements we add to this collection.
	 */
	public ArrayIndexedCollection(Collection col){
		this(col, DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor that receives a reference to another collection as a parameter and then copies all the elements
	 * from the received collection to this collection. 
	 * Sets the capacity to received parameter initialCapacity or to the size of the received collection if its larger.
	 * @param col Reference to the collection we want to copy.
	 * @param initialCapacity Wanted array size. Ignored if lower then size of the given collection.
	 */
	public ArrayIndexedCollection(Collection col, int initialCapacity) {
		if(initialCapacity < 1){
			throw new IllegalArgumentException("Ne može se stvoriti kolekcija sa veličinom  < 1.");
		}
			
		if(col != null && col.size() > initialCapacity){
			capacity = col.size();
		} else if(col != null) {
			capacity = initialCapacity;
		} else {
			capacity = initialCapacity;
			size = 0;
			elements = new Object[capacity];
			return;
		}
		
		elements = new Object[capacity];
		addAll(col);
	}
	
	/**
	 * Adds the given object into this collection on the first free space in the array.
	 * Can't send null as parameter, throws an exception in that case.
	 * @param value Object we want to add.
	 */
	public void add(Object value){
		if(value == null){
			throw new IllegalArgumentException("Kolekcija ne prihvaća null.");
		}
		
		if(size == capacity){
			doubleCapacity();
		}
		
		elements[size] = value;
		size++;
	}
	
	/**
	 * Returns the object that is stored in backing array at position index. 
	 * Valid indexes are 0 to size-1. If index is invalid method throws an IndexOutOfBoundsException.
	 * @param index Represents the position from which we want to get an object
	 * @return Returns an object from the index position.
	 */
	public Object get(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		
		return elements[index];
	}

	/**
	 * Removes all elements from the collection. The allocated array is left at current capacity.
	 */
	public void clear(){
		for(int i = 0; i < size; i++){
			elements[i] = null;
		}
		size = 0;
	}

	/**
	 * Method inserts an element on given position in the collection and shifts the rest
	 * of the collection (for positions > position) one place further so no overlapping occurs.
	 * @param value Value we wish to insert.
	 * @param position Position on which we want to insert.
	 */
	public void insert(Object value, int position){
		if(value == null){
			throw new IllegalArgumentException("Kolekcija ne prihvaća null.");
		}
		if(position < 0 || position > size){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		if(size == capacity){
			doubleCapacity();
		}
		
		int i;
		for(i = size; i > position; i--){
			elements[i] = elements[i-1];
		}
		elements[i] = value;
		size++;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the given value or -1 
	 * if the value is not found.
	 * @param value Value that is searched for in the collection.
	 * @return Returns the index of the first occurrence or -1 if not found.
	 */
	public int indexOf(Object value){
		if(value == null){
			return -1;
		}
		for(int i = 0; i < size; i++){
			if(elements[i].equals(value)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Method which should return the number of elements in the collection.
	 * @return Returns size of the collection.
	 */
	public int size(){
		return size;
	}

	/**
	 * Method that checks is the given param already in the collection. 
	 * @param value An object that we are checking is it in the collection.
	 * @return true if value is in collection. False otherwise.
	 */
	public boolean contains(Object value){
		if(indexOf(value) == -1){
			return false;
		}
		return true;
	}
	
	/**
	 * Removes element at specified index from collection. Element that was previously at location index+1
	 * after this operation is on location index, etc.
	 * @param index Position from which we want to remove an object in a collection.
	 */
	public void remove(int index){
		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		size--;
		
		int i;
		for(i = index; i < size; i++){
			elements[i] = elements[i+1];
		}
		elements[i] = null;
	}
	
	/**
	 * Method that removes an object from a collection if it's the same as the given param.
	 * @param value An object that should be removed if in collection.
	 * @return Returns true if object is removed, false otherwise.
	 */
	public boolean remove(Object value){
		int index = this.indexOf(value);
		if(index == -1){
			return false;
		}
		remove(index);
		return true;
	}
	/**
	 * Allocates new array with size equals to the size of this collections, fills it with 
	 * collection content and returns the array.
	 * @return Returns an array consisting with elements of the collection.
	 */
	public Object[] toArray(){
		Object[] array = new Object[size];
		for(int i = 0; i < size; i++){
			array[i] = elements[i];
		}
		return array;
	}	
	
	/**
	 * Method calls processor.process() for each element of this collection.
	 * @param processor An instance of the Processor class that will call .process() method.
	 */	
	public void forEach(Processor processor){
		for(int i = 0; i < this.size(); i++){
			processor.process(elements[i]);
		}
	}
	/**
	 * Method that reallocates the collections array and doubles its size
	 */
	private void doubleCapacity(){
		capacity *= 2;
		Object[] newArray = new Object[capacity];
		for(int i = 0; i < size; i++){
			newArray[i] = elements[i];
		}
		elements = newArray;
	}
}

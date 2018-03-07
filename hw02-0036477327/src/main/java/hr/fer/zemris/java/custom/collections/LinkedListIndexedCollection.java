package hr.fer.zemris.java.custom.collections;

/**
 * Class that represents a double-linked list collection.
 * Class isn't designed to hold null values.
 * @author Branko Sirotković
 * @version 1.0
 */
public class LinkedListIndexedCollection extends Collection{

	/**
	 * Class that represent a node in a linkedlist collection
	 * @author Branko Sirotković
	 */
	private static class ListNode{
		/**
		 * Next node to the current one in the linked list.
		 */
		private ListNode next = null;
		/**
		 * Previous node to the current one in the linked list.
		 */
		private ListNode previous = null;
		/**
		 * Value of the node.
		 */
		private Object value;
	}
	
	/**
	 * Reference to the first node of the double-linked list.
	 */
	private ListNode first;
	/**
	 * Reference to the last node of the double-linked list.
	 */
	private ListNode last;
	
	/**
	 * Current size of collection (number of elements actually stored; number of nodes in list). 
	 */
	private int size;
	
	/**
	 * Default constructor. Initializes variables: nodes to null and size to 0.
	 */
	public LinkedListIndexedCollection(){
		size = 0;
		first = null;
		last = null;
	}
	
	/**
	 * Constructor which receives another collection as a parameter. Each element of the received collection is added to this collection.
	 * @param col Collection that we want to copy in this collection.
	 */
	public LinkedListIndexedCollection(Collection col){
		this();
		if(col != null){
			addAll(col);			
		}
	}

	/**
	 * Adds the given object into this collection at the end of collection; newly added 
	 * element becomes the element at the biggest index.
	 * @param value An element we add to the collection.
	 */
	public void add(Object value){
		if(value == null){
			throw new IllegalArgumentException("Ne može se dodati null u listu.");
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;
		if(size == 0){
			first = newNode;
		}
		else{
			last.next = newNode;
			newNode.previous = last;
		}
		last = newNode;
		size++;
	}

	/**
	 * Returns the object that is stored in linked list at position index.
	 * @param index Position from which we want to get the object. Valid indexes are [0, size -1].
	 * @return Returns an object from position index in the collection.
	 */
	public Object get(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		
		ListNode temp;
		if(index <= size/2){
			temp = first;
			for(int i = 0; i != index; i++){
				temp = temp.next;
			}
		}
		else{
			temp = last;
			for(int i = size - 1; i != index; i--){
				temp = temp.previous;
			}
		}
		return temp.value;
	}

	/**
	 * Removes all elements from the collection.
	 */
	public void clear(){
		first = null;
		last = null;
		size = 0;
	}
	
	/**
	 * Method that checks is the given parameter already in the collection. 
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
	 * Inserts (does not overwrite) the given value at the given position in linked-list. 
	 * Elements starting from this position are shifted one position.
	 * @param value Value we wish to insert.
	 * @param position Position on which we want to insert.
	 */
	public void insert(Object value, int position){
		if(value == null){
			throw new IllegalArgumentException("Ne može se dodati null u listu.");
		}
		if(position < 0 || position > size){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;

		if(position == 0){			
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}
		else if(position == size){
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
		}
		else{
			ListNode tempPrevious = first;
			for(int i = 0; i != position - 1; i++){
				tempPrevious = tempPrevious.next;
			}
			
			ListNode tempNext = tempPrevious.next;
			tempPrevious.next = newNode;
			tempNext.previous = newNode;
			newNode.next = tempNext;
			newNode.previous = tempPrevious;
		}
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
		ListNode temp = first;
		int i = 0;
		while(temp != null){
			if(temp.value.equals(value)){
				return i;
			}
			temp = temp.next;
			i++;
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
	 * Removes element at specified index from collection. Element that was previously at location index+1
	 * after this operation is on location index, etc.
	 * @param index Position from which we want to remove an object in a collection.
	 */
	public void remove(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("Neispravan index, izvan raspona kolekcije.");
		}
		
		if(index == 0){
			first = first.next;
			first.previous = null;
		}
		else if(index == size-1){
			last = last.previous;
			last.next = null;
		}
		else{
			ListNode temp = first;
			for(int i = 0; i != index; i++){
				temp = temp.next;
			}
			
			temp.previous.next = temp.next;
			temp.next.previous = temp.previous;
			temp = null;
		}
		size--;
	}

	/**
	 * Allocates new array with size equals to the size of this collections, fills it with 
	 * collection content and returns the array.
	 * @return Returns an array consisting with elements of the collection.
	 */
	public Object[] toArray(){
		Object[] array = new Object[size];
		ListNode temp = first;
		for(int i = 0; i < size; i++){
			array[i] = temp.value;
			temp = temp.next;
		}
		return array;
	}

	/**
	 * Method calls processor.process() for each element of this collection.
	 * @param processor An instance of the Processor class that will call .process() method.
	 */	
	public void forEach(Processor processor){
		ListNode temp = first;
		while(temp != null){
			processor.process(temp.value);
			temp = temp.next;
		}
	}
	
	/**
	 * Method that removes an object from a collection if it's the same as the given param.
	 * @param value An object that should be removed if in collection.
	 * @return Returns true if object is removed, false otherwise.
	 */
	public boolean remove(Object value){
		int index = indexOf(value);
		
		if(index == -1){
			return false;
		}
		else{
			this.remove(index);
			return true;
		}
	}
}

package hr.fer.zemris.java.custom.collections.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;
import hr.fer.zemris.java.custom.collections.Processor;

/**
 * Demonstration class for classes ArrayIndexedCollection and LinkedListIndexedCollection.
 * @author Branko Sirotković
 *
 */
public class CollectionsDemo {

	/**
	 * Method that is called when program starts running.
	 * @param args Arguments of the command prompt.
	 */
	public static void main(String[] args) {
		class P extends Processor {
			public void process(Object o) {
				System.out.println(o);
			}
		};
		
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(new Integer(20));
		col.add("New York");
		col.add("San Francisco"); // here the internal array is reallocated to 4
		System.out.println(col.contains("New York")); // writes: true
		col.remove(1); // removes "New York"; shifts "San Francisco" to position 1
		System.out.println(col.get(1)); // writes: "San Francisco"
		System.out.println(col.size()); // writes: 2
		col.add("Los Angeles");
		LinkedListIndexedCollection col2 = new LinkedListIndexedCollection(col);
		System.out.println("Velicina je: " + col2.size());
		col2.insert(24, 0);
		System.out.println("Velicina je: " + col2.size());
		col2.insert(34, col2.size());
		System.out.println("Velicina je: " + col2.size());
		col2.insert(100, 4);
		System.out.println("Insert radi ako se sada ispiše 100: " + col2.get(4));
		col2.add("13");
		System.out.println("Velicina je: " + col2.size());
		col2.add(3.13);
		System.out.println("Velicina je: " + col2.size());
		col2.forEach(new P());
		System.out.println("Test get metode: " + col2.get(col2.size()-1) + " " + col2.size());
		// This is local class representing a Processor which writes objects to System.out
		System.out.println("col elements:");
		col.forEach(new P());
		System.out.println("col elements again:");
		System.out.println(Arrays.toString(col.toArray()));
		System.out.println("col2 elements:");
		col2.forEach(new P());
		col2.remove(3.13);
		System.out.println("col2 elements again:");
		System.out.println(Arrays.toString(col2.toArray()));
		System.out.println(col.contains(col2.get(1))); // true
		System.out.println(col2.contains(col.get(1))); // true
		col.remove(new Integer(20)); // removes 20 from collection (at position 0).

	}

}

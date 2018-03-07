package hr.fer.zemris.java.hw02.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

public class ArrayIndexedCollectionTest {
	
	@Test
	public void testArrayIndexedCollectionConstructor4() {
		ArrayIndexedCollection col = new ArrayIndexedCollection();
		col.add(2);
		col.add(1);
		ArrayIndexedCollection col2 = new ArrayIndexedCollection(col);
		col2.add(3);
		
		assertEquals(1, col.get(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArrayIndexedCollectionAddNull() {
		ArrayIndexedCollection col = new ArrayIndexedCollection();
		int prevSize = col.size();
		col.add(null);
		
		assertEquals(prevSize, col.size());
	}
	
	@Test
	public void testArrayIndexedCollectionAddNormal() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		int prevSize = col.size();
		col.add(44);
		
		assertEquals(prevSize + 1, col.size());
	}
	
	@Test
	public void testArrayIndexedCollectionAddDoubleSize() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		
		assertEquals(3, col.size());
	}
	
	@Test
	public void testArrayIndexedCollectionGet() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		
		assertEquals(1, col.get(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testArrayIndexedCollectionGetFail() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		
		assertEquals(1, col.get(3));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testArrayIndexedCollectionGetFail2() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		
		assertEquals(1, col.get(-1));
	}
	
	@Test
	public void testArrayIndexedCollectionClear() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		
		col.clear();
		assertEquals(0, col.size());
	}
	
	@Test
	public void testArrayIndexedCollectionInsert() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		
		assertEquals(5, (int)col.get(0) + (int)col.get(1));
	}
	
	@Test
	public void testArrayIndexedCollectionIndexOf() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		
		assertEquals(1, col.indexOf(2));
	}
	
	@Test
	public void testArrayIndexedCollectionIndexOfFail() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		
		assertEquals(-1, col.indexOf(21));
	}
	
	@Test
	public void testArrayIndexedCollectionContains() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		
		assertEquals(true, col.contains(23));
	}
	
	@Test
	public void testArrayIndexedCollectionDoesntContain() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		
		assertEquals(false, col.contains(24));
	}
	
	@Test
	public void testArrayIndexedCollectionRemoveIndex() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		col.remove(3);
		
		assertEquals(1, col.get(3));
	}
	
	@Test
	public void testArrayIndexedCollectionRemoveValue() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		col.add("2");
		boolean isIt = col.remove("2");
		
		assertEquals(true, isIt);
	}
	
	@Test
	public void testArrayIndexedCollectionRemoveValueFail() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(44);
		col.add(23);
		col.add(1);
		col.insert(2, 0);
		col.insert(3, 0);
		col.add("2");
		boolean isIt = col.remove("3");
		
		assertEquals(false, isIt);
	}
	

}

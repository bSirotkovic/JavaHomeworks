package hr.fer.zemris.java.hw01;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniqueNumbersTest {

	@Test
	public void testContainsValue() {
		UniqueNumbers.TreeNode root = null; 
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 1);
		root = UniqueNumbers.addNode(root, -3);
		root = UniqueNumbers.addNode(root, 100);
		assertEquals(true, UniqueNumbers.containsValue(root, -3));
	}
	
	@Test
	public void failContainsValue() {
		UniqueNumbers.TreeNode root = null; 
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 1);
		root = UniqueNumbers.addNode(root, -3);
		root = UniqueNumbers.addNode(root, 100);
		assertEquals(false, UniqueNumbers.containsValue(root, 5));
	}

	@Test
	public void testTreeSize() {
		UniqueNumbers.TreeNode root = null; 
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 1);
		root = UniqueNumbers.addNode(root, -3);
		root = UniqueNumbers.addNode(root, 100);
		assertEquals(4, UniqueNumbers.treeSize(root));
	}

	@Test
	public void testAddNode() {
		UniqueNumbers.TreeNode root = null;
		root = UniqueNumbers.addNode(root, 3);
		root = UniqueNumbers.addNode(root, 4);
		root = UniqueNumbers.addNode(root, 5);
		
		assertEquals(5, root.right.right.value);
	}
	
	@Test
	public void testDuplicateNode(){
		UniqueNumbers.TreeNode root = null;
		root = UniqueNumbers.addNode(root, 3);
		root = UniqueNumbers.addNode(root, 3);
		root = UniqueNumbers.addNode(root, 5);
		
		assertEquals(null, root.right.right);
	}

}

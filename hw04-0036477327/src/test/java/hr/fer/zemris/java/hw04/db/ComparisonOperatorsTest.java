package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComparisonOperatorsTest {

	@Test
	public void testLess1() {
		assertEquals(ComparisonOperators.LESS.satisfied("Branko", "Sirotković"), true);
	}
	
	@Test
	public void testLess2() {
		assertEquals(ComparisonOperators.LESS.satisfied("Branko", "Branko"), false);
	}
	
	@Test
	public void testLess3() {
		assertEquals(ComparisonOperators.LESS.satisfied("Sirotković", "Branko"), false);
	}
	
	@Test
	public void testLessEqual1() {
		assertEquals(ComparisonOperators.LESS_OR_EQUALS.satisfied("Branko", "Sirotković"), true);
	}
	
	@Test
	public void testLessEqual2() {
		assertEquals(ComparisonOperators.LESS_OR_EQUALS.satisfied("Branko", "Branko"), true);
	}
	
	@Test
	public void testLessEqual3() {
		assertEquals(ComparisonOperators.LESS_OR_EQUALS.satisfied("Sirotković", "Branko"), false);
	}
	
	@Test
	public void testGreater1() {
		assertEquals(ComparisonOperators.GREATER.satisfied("Branko", "Sirotković"), false);
	}
	
	@Test
	public void testGreater2() {
		assertEquals(ComparisonOperators.GREATER.satisfied("Branko", "Branko"), false);
	}
	
	@Test
	public void testGreater3() {
		assertEquals(ComparisonOperators.GREATER.satisfied("Sirotković", "Branko"), true);
	}
	
	@Test
	public void testGreaterEqual1() {
		assertEquals(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Branko", "Sirotković"), false);
	}
	
	@Test
	public void testGreaterEqual2() {
		assertEquals(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Branko", "Branko"), true);
	}
	
	@Test
	public void testGreaterEqual3() {
		assertEquals(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Sirotković", "Branko"), true);
	}
	
	@Test
	public void testEqual1() {
		assertEquals(ComparisonOperators.EQUALS.satisfied("Branko", "Sirotković"), false);
	}
	
	@Test
	public void testEqual2() {
		assertEquals(ComparisonOperators.EQUALS.satisfied("Branko", "Branko"), true);
	}
	
	@Test
	public void testNotEqual1() {
		assertEquals(ComparisonOperators.NOT_EQUALS.satisfied("Sirotković", "Branko"), true);
	}
	
	@Test
	public void testNotEqual2() {
		assertEquals(ComparisonOperators.NOT_EQUALS.satisfied("Branko", "Branko"), false);
	}
	
	@Test
	public void testLike1() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Branko", "B*o"), true);
	}
	
	@Test
	public void testLike2() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Sirotković", "*ović"), true);
	}
	
	@Test
	public void testLike3() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Sirotković", "Sir*"), true);
	}
	
	@Test
	public void testLike4() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Sirotković", "*"), true);
	}
	
	@Test
	public void testLike5() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Sirotković", "B*"), false);
	}
	
	@Test
	public void testLike6() {
		assertEquals(ComparisonOperators.LIKE.satisfied("Sirotković", "*p"), false);
	}
	

}

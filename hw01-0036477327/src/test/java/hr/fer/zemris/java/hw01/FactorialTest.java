package hr.fer.zemris.java.hw01;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void oneFactorial() {
		assertEquals(1, Factorial.factorial(1));
	}
	
	@Test
	public void tenFactorial() {
		assertEquals(3628800, Factorial.factorial(10));
	}
	
	@Test
	public void tooSmallFactorial() {
		assertEquals(0, Factorial.factorial(-13));
	}
	
	@Test
	public void tooBigFactorial() {
		assertEquals(0, Factorial.factorial(101));
	}

}

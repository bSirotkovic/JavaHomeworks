package hr.fer.zemris.math;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ComplexTest {

	@Test
	public void testModule() {
		Complex actual = new Complex(3, -4);
		assertEquals(5, actual.module(), 0);
	}

	@Test
	public void testMultiply() {
		Complex temp = new Complex(3.14, 2.4);
		Complex actual = new Complex(0.3, -2).multiply(temp);
		Complex expected = new Complex(5.742, -5.56);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testDivide() {
		Complex temp = new Complex(3.14, 2.4);
		Complex actual = new Complex(0.3, -2).divide(temp);
		Complex expected = new Complex(-0.246997362, -0.448154882);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testAdd() {
		Complex temp = new Complex(3.14, 2.4);
		Complex actual = new Complex(0.3, -2).add(temp);
		Complex expected = new Complex(3.44, 0.4);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testSub() {
		Complex temp = new Complex(3.14, 2.4);
		Complex actual = new Complex(0.3, -2).sub(temp);
		Complex expected = new Complex(-2.84, -4.4);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testNegate() {
		Complex actual = new Complex(0.3, -2).negate();
		Complex expected = new Complex(-0.3, 2);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testPower() {
		Complex actual = new Complex(0.3, -2).power(4);
		Complex expected = new Complex(13.8481, 9.38400);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testRoot() {
		List<Complex> actual = new Complex(0.3, -2).root(4);
		Complex expected[] = {
				new Complex(1.11796, -0.415041),
				new Complex(0.415041, 1.11796),
				new Complex(-1.11796, 0.415041),
				new Complex(-0.415041, -1.11796)
		};
		
		for(int i = 0; i < 4; i++){
			assertEquals(expected[i], actual.get(i));			
		}
	}

}

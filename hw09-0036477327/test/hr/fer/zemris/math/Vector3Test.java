package hr.fer.zemris.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class Vector3Test {

	@Test
	public void testNorm1() {
		assertEquals(new Vector3(1,2,3).norm(), 3.74165738677, 1e-6);
	}
	
	@Test
	public void testNorm2() {
		assertEquals(new Vector3(-3.14, 4.2, -0.1).norm(), 5.24495948507, 1e-6);
	}

	@Test
	public void testNormalized() {
		Vector3 expected = new Vector3(-0.59867, 0.800769, -0.0190659);
		Vector3 actual = new Vector3(-3.14, 4.2, -0.1).normalized();
		
		assertEquals(expected.getX(), actual.getX(), 1e-4);
		assertEquals(expected.getY(), actual.getY(), 1e-4);
		assertEquals(expected.getZ(), actual.getZ(), 1e-4);
	}

	@Test
	public void testAdd() {
		Vector3 expected = new Vector3(-5.14, 4.3, 0.9);
		Vector3 temp = new Vector3(-2, 0.1, 1);
		Vector3 actual = new Vector3(-3.14, 4.2, -0.1).add(temp);
		
		assertEquals(expected.getX(), actual.getX(), 1e-4);
		assertEquals(expected.getY(), actual.getY(), 1e-4);
		assertEquals(expected.getZ(), actual.getZ(), 1e-4);
	}

	@Test
	public void testSub() {
		Vector3 expected = new Vector3(-1.14, 4.1, -1.1);
		Vector3 temp = new Vector3(-2, 0.1, 1);
		Vector3 actual = new Vector3(-3.14, 4.2, -0.1).sub(temp);
		
		assertEquals(expected.getX(), actual.getX(), 1e-4);
		assertEquals(expected.getY(), actual.getY(), 1e-4);
		assertEquals(expected.getZ(), actual.getZ(), 1e-4);
	}

	@Test
	public void testDot() {
		Vector3 temp = new Vector3(-2, 0.1, 1);
		double actual = new Vector3(-3.14, 4.2, -0.1).dot(temp);
		
		assertEquals(6.6, actual, 1e-5);
	}

	@Test
	public void testCross() {
		Vector3 expected = new Vector3(-1, -4, 3);
		Vector3 temp = new Vector3(1, 5, 7);
		Vector3 actual = new Vector3(1, 2, 3).cross(temp);
		
		assertEquals(expected.getX(), actual.getX(), 1e-4);
		assertEquals(expected.getY(), actual.getY(), 1e-4);
		assertEquals(expected.getZ(), actual.getZ(), 1e-4);
	}

	@Test
	public void testScale() {
		Vector3 expected = new Vector3(6.28, -8.4, 0.2);
		
		Vector3 actual = new Vector3(-3.14, 4.2, -0.1).scale(-2);
		
		assertEquals(expected.getX(), actual.getX(), 1e-4);
		assertEquals(expected.getY(), actual.getY(), 1e-4);
		assertEquals(expected.getZ(), actual.getZ(), 1e-4);
	}

	@Test
	public void testCosAngle() {
		Vector3 temp = new Vector3(-2, 0.1, 1);
		double actual = new Vector3(-3.14, 4.2, -0.1).cosAngle(temp);
		
		assertEquals(0.5621897650520957, actual, 1e-6);
	}

}

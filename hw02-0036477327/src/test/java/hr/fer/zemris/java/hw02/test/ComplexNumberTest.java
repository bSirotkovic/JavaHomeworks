package hr.fer.zemris.java.hw02.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.hw02.ComplexNumber;

public class ComplexNumberTest {

	@Test
	public void testFromReal() {
		assertEquals(ComplexNumber.fromReal(3), new ComplexNumber(3, 0));
	}

	@Test
	public void testFromImaginary() {
		assertEquals(ComplexNumber.fromImaginary(3), new ComplexNumber(0, 3));
	}

	@Test
	public void testFromMagnitudeAndAngle() {
		assertEquals(ComplexNumber.fromMagnitudeAndAngle(56, 4.34), new ComplexNumber(-20.375, -52.162));
	}

	@Test
	public void testParse1() {
		assertEquals(new ComplexNumber(3,0), ComplexNumber.parse("3"));
	}
	
	@Test
	public void testParse2() {
		assertEquals(new ComplexNumber(0,4), ComplexNumber.parse("4i"));
	}
	
	@Test
	public void testParse3() {
		assertEquals(new ComplexNumber(0,1), ComplexNumber.parse("i"));
	}
	
	@Test
	public void testParse4() {
		assertEquals(new ComplexNumber(0,-1), ComplexNumber.parse("-i"));
	}
	
	@Test
	public void testParse5() {
		assertEquals(new ComplexNumber(0,-2.13), ComplexNumber.parse("-2.13i"));
	}
	
	@Test
	public void testParse6() {
		assertEquals(new ComplexNumber(2.34,3), ComplexNumber.parse("2.34+3i"));
	}
	
	@Test
	public void testParse7() {
		assertEquals(new ComplexNumber(-2,4.1), ComplexNumber.parse("-2+4.1i"));
	}
	
	@Test
	public void testParse8() {
		assertEquals(new ComplexNumber(4.1,-1), ComplexNumber.parse("4.1-i"));
	}

	@Test
	public void testParse9() {
		assertEquals(new ComplexNumber(3,-2.5), ComplexNumber.parse("3-2.5i"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testParse10() {
		assertEquals(new ComplexNumber(3,-2.5), ComplexNumber.parse("branko"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testParse11() {
		assertEquals(new ComplexNumber(3,-2.5), ComplexNumber.parse("+-"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testParse12() {
		assertEquals(new ComplexNumber(3,-2.5), ComplexNumber.parse("23+-"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testParse13() {
		assertEquals(new ComplexNumber(3,-2.5), ComplexNumber.parse(" "));
	}

	@Test
	public void testGetMagnitude() {
		assertEquals(Math.sqrt(10), new ComplexNumber(3, -1).getMagnitude(), 1e-4);
	}

	@Test
	public void testGetAngle1() {
		assertEquals(5*Math.PI/4, new ComplexNumber(-1, -1).getAngle(), 1e-4);
	}
	
	@Test
	public void testGetAngle2() {
		assertEquals(7*Math.PI/4, new ComplexNumber(1, -1).getAngle(), 1e-4);
	}
	
	@Test
	public void testGetAngle3() {
		assertEquals(3*Math.PI/4, new ComplexNumber(-1, 1).getAngle(), 1e-4);
	}

	@Test
	public void testAdd() {
		assertEquals(new ComplexNumber(9,0), new ComplexNumber(4, 1).add(new ComplexNumber(5,-1)));
	}

	@Test
	public void testSub() {
		assertEquals(new ComplexNumber(-3, 4), new ComplexNumber(2, 3).sub(new ComplexNumber(5,-1)));
	}

	@Test
	public void testMul() {
		assertEquals(new ComplexNumber(13, 13), new ComplexNumber(2, 3).mul(new ComplexNumber(5,-1)));
	}

	@Test
	public void testDiv() {
		assertEquals(new ComplexNumber(7./26, 17./26), new ComplexNumber(2, 3).div(new ComplexNumber(5,-1)));
	}

	@Test
	public void testPower() {
		assertEquals(new ComplexNumber(-46, 9), new ComplexNumber(2, 3).power(3));
	}

	@Test
	public void testRoot() {
		assertEquals("3.000 + 1.000i", new ComplexNumber(3,1).toString());
	}

	@Test
	public void testToString() {
		assertEquals("3.000 + 1.000i", new ComplexNumber(3,1).toString());
	}

}

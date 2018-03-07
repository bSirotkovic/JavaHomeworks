package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValueWrapperTest {

	@Test
	public void twoNullsAdd() {
		ValueWrapper value1 = new ValueWrapper(null);
		ValueWrapper value2 = new ValueWrapper(null);
		
		value1.add(value2.getValue());
		
		assertEquals(0, value1.getValue());
	}
	
	@Test
	public void NullAndStringSub() {
		ValueWrapper value1 = new ValueWrapper("3");
		ValueWrapper value2 = new ValueWrapper(null);
		
		value1.subtract(value2.getValue());
		
		assertEquals(3, value1.getValue());
	}
	
	@Test
	public void TwoStringMul1() {
		ValueWrapper value1 = new ValueWrapper("3");
		ValueWrapper value2 = new ValueWrapper("3.14");
		
		value1.multiply(value2.getValue());
		
		assertEquals(9.42, value1.getValue());
	}
	
	@Test
	public void TwoStringMul2() {
		ValueWrapper value1 = new ValueWrapper("3e-1");
		ValueWrapper value2 = new ValueWrapper("3.14");
		
		value1.multiply(value2.getValue());
		
		assertEquals(0.942, value1.getValue());
	}
	
	@Test
	public void StringAndDoubleCompare() {
		ValueWrapper value1 = new ValueWrapper(3.14);
		ValueWrapper value2 = new ValueWrapper("3.14");
		
		assertEquals(0, value1.numCompare(value2.getValue()));
	}
	
	@Test
	public void StringAndIntegerCompare() {
		ValueWrapper value1 = new ValueWrapper(3);
		ValueWrapper value2 = new ValueWrapper("3.14");
		
		assertEquals(-1, value1.numCompare(value2.getValue()));
	}
	
	@Test
	public void IntegerAndDoubleAdd() {
		ValueWrapper value1 = new ValueWrapper(3);
		ValueWrapper value2 = new ValueWrapper(3.0);
		
		value1.add(value2.getValue());
		
		assertEquals(6.0, value1.getValue());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void WrongString() {
		ValueWrapper value1 = new ValueWrapper(3);
		ValueWrapper value2 = new ValueWrapper("gluposti");
		
		value1.add(value2.getValue());
		
		assertEquals(6.0, value1.getValue());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void WrongStringDouble() {
		ValueWrapper value1 = new ValueWrapper(3);
		ValueWrapper value2 = new ValueWrapper("3.0.0");
		
		value1.add(value2.getValue());
		
		assertEquals(6.0, value1.getValue());
	}

}

package hr.fer.zemris.java.hw06.crypto;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {
	
	@Test
	public void testHexToByte() {
		byte[] result = {77, 119};
		
		assertArrayEquals(result, Util.hexToByte("4d77"));
	}
	
	@Test
	public void testHexToByteNegativeByte() {
		byte[] result = {-17};
		
		assertArrayEquals(result, Util.hexToByte("ef"));
	}
	
	@Test
	public void testHexToByteUneven() {
		byte[] result = {10, 11};
		
		assertArrayEquals(result, Util.hexToByte("a0b"));
	}
	
	@Test
	public void testHexToByteUpperCase() {
		byte[] result = {10, 11, 12, 13};
		
		assertArrayEquals(result, Util.hexToByte("0A0B0c0D"));
	}
	
	@Test
	public void testHexToByteEmptyString() {
		byte[] result = {};
		
		assertArrayEquals(result, Util.hexToByte(""));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testHexToByteWrongDigits() {
		byte[] result = new byte[0];
		
		assertArrayEquals(result, Util.hexToByte("123T"));
	}
	
	@Test
	public void testHexToByteMaxMin() {
		byte[] result = {-1, -128, 127};
		
		assertArrayEquals(result, Util.hexToByte("FF807F"));
	}
	
	@Test
	public void testByteToHex() {
		byte[] bytes = {10, 11};
		
		assertEquals("0a0b", Util.byteToHex(bytes));
	}
	
	@Test
	public void testByteToHexNegative() {
		byte[] bytes = {10, 11, -17};
		
		assertEquals("0a0bef", Util.byteToHex(bytes));
	}
	
	@Test
	public void testByteToHexMinMax() {
		byte[] bytes = {127, -128, -1};
		
		assertEquals("7f80ff", Util.byteToHex(bytes));
	}
	
	@Test
	public void testByteToHexEmpty() {
		byte[] bytes = {};
		
		assertEquals("", Util.byteToHex(bytes));
	}

}

package hr.fer.zemris.bf.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndexToByteArrayTest {

	@Test
	public void test1() {
		byte[] expected = {1, 1, 1};
		byte[] actual = Util.indexToByteArray(7, 3);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		byte[] expected = {0, 0, 1, 1, 1};
		byte[] actual = Util.indexToByteArray(7, 5);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		byte[] expected = {0, 0, 0, 0, 1, 1};
		byte[] actual = Util.indexToByteArray(3, 6);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		byte[] expected = {0, 0, 0, 0, 0, 0};
		byte[] actual = Util.indexToByteArray(0, 6);
		assertArrayEquals(expected, actual);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test5() {
		byte[] expected = {0, 0, 0, 0, 0, 0};
		byte[] actual = Util.indexToByteArray(0, 0);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test6() {
		byte[] expected = {0, 0, 0};
		byte[] actual = Util.indexToByteArray(120, 3);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test7() {
		byte[] expected = {1, 1, 1, 1, 1, 0};
		byte[] actual = Util.indexToByteArray(-2, 6);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test8() {
		byte[] expected = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0};
		byte[] actual = Util.indexToByteArray(-2, 32);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test9() {
		byte[] expected = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0};
		byte[] actual = Util.indexToByteArray(-2, 33);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test10() {
		byte[] expected = {0, 0, 1, 1};
		byte[] actual = Util.indexToByteArray(19, 4);
		assertArrayEquals(expected, actual);
	}

}

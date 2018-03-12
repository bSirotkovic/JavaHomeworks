package hr.fer.zemris.java.hw01;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testGiveArea() {
		assertEquals(4.0, Rectangle.giveArea(2, 2), 0);
	}
	
	@Test
	public void failGiveArea() {
		assertEquals(0, Rectangle.giveArea(-23, 2), 0);
	}
	
	@Test
	public void testGivePerimeter() {
		assertEquals(8, Rectangle.givePerimeter(2, 2), 0);
	}
	
	@Test
	public void failGivePerimeter() {
		assertEquals(0, Rectangle.givePerimeter(-23, 2), 0);
	}
	
}

package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObjectMultistackTest {

	public ObjectMultistack loadMap(){
		ObjectMultistack stack = new ObjectMultistack();
		
		stack.push("branko", new ValueWrapper("1"));
		stack.push("branko", new ValueWrapper("2"));
		stack.push("branko", new ValueWrapper("3"));
		stack.push("branko", new ValueWrapper("4"));
		stack.push("branko2", new ValueWrapper("5"));
		stack.push("branko2", new ValueWrapper("6"));
		stack.push("branko2", new ValueWrapper("7"));
		stack.push("branko2", new ValueWrapper("8"));
		stack.push("branko3", new ValueWrapper("1"));
		
		return stack;
	}
	
	@Test
	public void testPeek() {
		ObjectMultistack stack = loadMap();
		stack.pop("branko3");
		assertEquals(true, stack.isEmpty("branko3"));
	}
	
	@Test (expected=RuntimeException.class)
	public void testPeekFalse() {
		ObjectMultistack stack = loadMap();
		
		assertEquals(stack.peek("branko5").getValue(), "4");
	}
	
	@Test
	public void testisEmpty1() {
		ObjectMultistack stack = loadMap();
		
		stack.pop("branko3");
		
		assertEquals(stack.isEmpty("branko3"), true);
	}
	
	@Test
	public void testisEmpty2() {
		ObjectMultistack stack = loadMap();
		
		
		assertEquals(stack.isEmpty("branko2"), false);
	}

	@Test
	public void testPop() {
		ObjectMultistack stack = loadMap();
		
		stack.pop("branko2");
		
		assertEquals(stack.pop("branko2").getValue(), "7");
	}
}

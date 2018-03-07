package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldValueGetterTest {

	@Test
	public void testFirstName() {
		StudentRecord sr = new StudentRecord("1950","Sirotković","Branko", 5);
		assertEquals(FieldValueGetters.FIRST_NAME.get(sr), "Branko");
	}
	
	@Test
	public void testLastName() {
		StudentRecord sr = new StudentRecord("1950","Sirotković","Branko", 5);
		assertEquals(FieldValueGetters.LAST_NAME.get(sr), "Sirotković");
	}
	
	@Test
	public void testJMBAG() {
		StudentRecord sr = new StudentRecord("1950","Branko","Sirotković", 5);
		assertEquals(FieldValueGetters.JMBAG.get(sr), "1950");
	}

}

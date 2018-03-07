package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueryParserTest {

	@Test
	public void validQuery1() {
		QueryParser parser = new QueryParser("lastName = \"nešto\" And firstName > \"jošnešto\"");
		assertEquals(parser.getQuery().size(), 2);
	}
	
	@Test
	public void validQuery2() {
		QueryParser parser = new QueryParser("jmbag = \"bilo što\"");
		assertEquals(parser.isDirectQuery(), true);
		assertEquals(parser.getQueriedJMBAG(), "bilo što");
	}
	
	@Test
	public void validQuery3() {
		QueryParser parser = new QueryParser("jmbag > \"adasa\"");
		assertEquals(parser.isDirectQuery(), false);
	}

	@Test (expected=RuntimeException.class)
	public void invalidQuery1() {
		new QueryParser("gluposti");
	}
	
	@Test (expected=RuntimeException.class)
	public void invalidQuery2() {
		new QueryParser("firstName LIKE \"*asdasd*\"");
	}
	
	@Test (expected=RuntimeException.class)
	public void invalidQuery3() {
		new QueryParser("firstNam = 2");
	}
	
	@Test (expected=RuntimeException.class)
	public void invalidQuery4() {
		new QueryParser("firstName = \"branko\" lastName = \"svašta\"");
	}
	
	@Test (expected=RuntimeException.class)
	public void invalidQuery5() {
		new QueryParser("firstName = \"wild*card\"");
	}
	
	@Test (expected=RuntimeException.class)
	public void invalidQuery6() {
		new QueryParser("and like");
	}
}

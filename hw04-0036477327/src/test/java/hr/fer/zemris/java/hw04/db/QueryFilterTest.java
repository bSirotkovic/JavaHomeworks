package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class QueryFilterTest {

	public List<String> loader(){
		List<String> lines = null;
		try {
			lines = Files.readAllLines( Paths.get("src/main/resources/database.txt"), StandardCharsets.UTF_8 );
		} catch (IOException e) {
			System.out.println("Pogreška prilikom čitanja iz datoteke");
			System.exit(1);
		}
		
		return lines;
	}
	
	@Test
	public void test() {
		QueryParser parser = new QueryParser("firstName >= \"L\" aNd lastName LIKE \"*ić\"");
		QueryFilter filter = new QueryFilter(parser.getQuery());
		
		StudentDatabase sb = new StudentDatabase(loader());
		assertEquals(sb.filter(filter).size(), 14);
	}

}

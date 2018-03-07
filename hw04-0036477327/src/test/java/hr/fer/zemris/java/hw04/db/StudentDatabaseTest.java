package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class StudentDatabaseTest {

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
	public void testForJMBAG1() {
		StudentDatabase sb = new StudentDatabase(loader());
		
		assertEquals(sb.forJMBAG("0000000005").getFirstName(), "Jusufadis");
	}
	
	@Test
	public void testForJMBAG2() {
		StudentDatabase sb = new StudentDatabase(loader());
		
		assertNull(sb.forJMBAG("glupiJMBAG"));
	}
	
	@Test
	public void testFilter1() {
		StudentDatabase sb = new StudentDatabase(loader());
		
		assertEquals(sb.filter(record -> true).size(), 63);
	}
	
	@Test
	public void testFilter2() {
		StudentDatabase sb = new StudentDatabase(loader());
		
		assertEquals(sb.filter(record -> false).size(), 0);
	}

}

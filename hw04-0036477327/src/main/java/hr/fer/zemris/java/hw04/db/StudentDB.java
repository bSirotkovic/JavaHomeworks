package hr.fer.zemris.java.hw04.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Demo razred koji omogućava korisniku unos upita na standardni ulaz. Upiti se izvršavaju
 * nad StudentDatabase koja je inicijalizirana datotekom database.txt koja se nalazi u folderu main/resources.
 * @author Branko Sirotković
 *
 */
public class StudentDB {

	/**
	 * Metoda koja se pokreće pokretanjem programa.
	 * @param args Argumenti naredbenog retka. Ne koriste se.
	 */
	public static void main(String[] args) {
		
		List<String> lines = null;
		
		//Usprkos uputi datoteka database.txt se učitava sa logičnog mjesta, a to je folder resources.
		try {
			lines = Files.readAllLines( Paths.get("src/main/resources/database.txt"), StandardCharsets.UTF_8 );
		} catch (IOException e) {
			System.out.println("Pogreška prilikom čitanja iz datoteke");
			System.exit(1);
		}
		
		Scanner scanner = new Scanner(System.in);
		StudentDatabase db = null;
		try{
			db = new StudentDatabase(lines);			
		} catch (ArrayIndexOutOfBoundsException ex){
			System.out.println("Datoteka iz koje se učitavaju studenti ima studente zapisane u krivom formatu.");
			scanner.close();
			System.exit(1);
		} catch (IllegalArgumentException ex){
			System.out.println(ex.getMessage());
			scanner.close();
			System.exit(1);
		} 
		//Pokušao sam izbjeći dupliciranje koda, ali finally blok bi se svejedno izvršavao i kada nema iznimke?!
		
		System.out.print("> ");
		while(scanner.hasNextLine()){
			String query = scanner.nextLine().trim();
			
			if(query.equals("exit")){
				System.out.println("Goodbye!");
				System.exit(1);
			}
			
			if(!query.startsWith("query")){
				System.out.println("Niste zadali korektno query naredbu! Hint: treba započeti sa query.");
				System.out.print("> ");
				continue;
			}
			
			QueryParser parser = null;
			try{
				parser = new QueryParser(query.substring(5));
			} catch (RuntimeException ex){
				System.out.println(ex.getMessage());
				System.out.print("> ");
				continue;
			}
			
			if(parser.isDirectQuery()){
				ArrayList<StudentRecord> tempList = new ArrayList<>();
				tempList.add(db.forJMBAG(parser.getQueriedJMBAG()));
				
				System.out.println("Using index for record retrieval.");
				printStudents(tempList);
			}
			else{
				printStudents(db.filter(new QueryFilter(parser.getQuery())));
			}
			
			System.out.print("> ");
		}
		
		scanner.close();
	}

	/**
	 * Metoda koja ispisuje rezultate upita u formatiranom obliku.
	 * @param students Lista StudentRecord-a koji zadovoljavaju upit te ih se ispisuje.
	 */
	private static void printStudents(List<StudentRecord> students) {
		if(students.isEmpty()){
			System.out.println("Records selected: 0");
			return;
		}
		
		int maxNameLength = 0, maxSurnameLength = 0;
		
		for (StudentRecord studentRecord : students) {
			if(studentRecord.getFirstName().length() > maxNameLength)
				maxNameLength = studentRecord.getFirstName().length();
			
			if(studentRecord.getLastName().length() > maxSurnameLength)
				maxSurnameLength = studentRecord.getLastName().length();
		}
		
		StringBuilder sb = new StringBuilder("+============+");
		for(int i = 0; i < maxSurnameLength + 2; i++){
			sb.append("=");
		}
		sb.append("+");
		for(int i = 0; i < maxNameLength + 2; i++){
			sb.append("=");
		}
		sb.append("+===+");
		
		//početak ispisa.
		System.out.println(sb.toString());
		for (StudentRecord sr : students) {
			String format = "| %s | %-" + maxSurnameLength + "s | %-" + maxNameLength + "s | %d |%n";
			System.out.printf(format, sr.getJmbag(), sr.getLastName(), sr.getFirstName(), sr.getFinalGrade());
		}
		System.out.println(sb.toString() + "\nRecords selected: " + students.size());
		
	}

}

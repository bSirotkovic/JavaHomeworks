package hr.fer.zemris.java.hw05.demo4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Program koji prvo stvara improviziranu bazu podataka studenata. 
 * Te nad tom bazom izvodi osam upita koji se ispisuju na standardni izlaz.
 * @author Branko Sirotković
 *
 */
public class StudentDemo {

	/**
	 * Metoda koja se pokreće pokretanjem programa.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args) {

		List<String> lines = null;

		try {
			lines = Files.readAllLines(Paths.get("./studenti.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Neuspjelo čitanje datoteke.");
			System.exit(1);
		}

		List<StudentRecord> records = convert(lines);

		System.out.println("Broj studenata koji ukupno imaju preko 25 bodova: " + vratiBodovaViseOd25(records));

		System.out.println("Broj studenata koji imaju ocjenu 5: " + vratiBrojOdlikasa(records));

		System.out.println("\nOdlikasi:");
		for (StudentRecord studentRecord : vratiListuOdlikasa(records)) {
			System.out.println(studentRecord.getJmbag() + " " + studentRecord.getGrade());
		}

		System.out.println("\nOdlikasi sortirano:");
		for (StudentRecord studentRecord : vratiSortiranuListuOdlikasa(records)) {
			System.out.println(
					studentRecord.getJmbag() + " " + studentRecord.getGrade() + " " + studentRecord.getTotalPoints());
		}

		System.out.println("\nStudenti koji nisu položili ispit:");
		for (String string : vratiPopisNepolozenih(records)) {
			System.out.println(string);
		}

		for (Map.Entry<Integer, List<StudentRecord>> entry : razvrstajStudentePoOcjenama(records).entrySet()) {
			System.out.println("Ocjenu " + entry.getKey() + " imaju sljedeći studenti: ");
			for (StudentRecord studentRecord : entry.getValue()) {
				System.out.println(studentRecord.getJmbag());
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : vratiBrojStudenataPoOcjenama(records).entrySet()) {
			System.out.println("Ocjenu: " + entry.getKey() + " ima ovoliko studenata: " + entry.getValue());
		}

		for (Map.Entry<Boolean, List<StudentRecord>> entry : razvrstajProlazPad(records).entrySet()) {
			if(entry.getKey()){
				System.out.println("Studenti koji su položili ovaj predmet su: ");
			} else{
				System.out.println("Studenti koji nisu položili ovaj predmet su: ");
			}
			for (StudentRecord studentRecord : entry.getValue()) {
				System.out.println(studentRecord.getJmbag());
			}
		}
	}

	/**
	 * Pomoćna metoda koja konvertira ulaznu datoteku u konkretne studente.
	 * @param lines Lista redaka ulazne datoteke.
	 * @return Listu StudentRecord-a koje smo stvorili parsirajući ulaznu datoteku.
	 */
	private static List<StudentRecord> convert(List<String> lines) {
		ArrayList<StudentRecord> list = new ArrayList<StudentRecord>();

		for (String line : lines) {
			String[] studentInfo = line.split("\t");
			try{
				list.add(new StudentRecord(studentInfo));
			} catch (Exception ex){
				System.out.println("Predana je datoteka s neispravnim formatom zapisa studenata");
				System.exit(1);
			}
		}

		return list;
	}

	/**
	 * Metoda za upit koliko ima studenata koji imaju više od 25 bodova.
	 * @param records Lista studenata.
	 * @return Broj studenata koji imaju više od 25 bodova.
	 */
	private static long vratiBodovaViseOd25(List<StudentRecord> records) {
		return records.stream().filter(s -> (s.getTotalPoints() > 25)).toArray().length;
	}

	/**
	 * Metoda za upit koliko ima studenata koji imaju ocjenu 5.
	 * @param records Lista studenata.
	 * @return Broj studenata koji imaju ocjenu 5.
	 */
	private static long vratiBrojOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> (s.getGrade() == 5)).toArray().length;
	}

	/**
	 * Metoda za upit koji studenti imaju ocjenu 5.
	 * @param records Lista svih studenata.
	 * @return Listu svih studenata koji imaju ocjenu 5.
	 */
	private static List<StudentRecord> vratiListuOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> (s.getGrade() == 5)).collect(Collectors.toList());
	}

	/**
	 * Metoda za upit koji studenti imaju ocjenu 5, te da sortira te studente s obzirom
	 * koliko ukupno bodova imaju.
	 * @param records Lista svih studenata.
	 * @return Lista studenata koji imaju ocjenu 5, sortirana po broju bodova.
	 */
	private static List<StudentRecord> vratiSortiranuListuOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getGrade() == 5)
				.sorted((s1, s2) -> Double.compare(s2.getTotalPoints(), s1.getTotalPoints()))
				.collect(Collectors.toList());
	}

	/**
	 * Metoda za upit koji studenti nisu položili predmet odnosno imaju ocjenu 1.
	 * @param records Lista svih studenata.
	 * @return Lista studenata koji nisu položili predmet.
	 */
	private static List<String> vratiPopisNepolozenih(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getGrade() == 1).map(s -> s.getJmbag())
				.sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
	}

	/**
	 * Metoda za upit koji vraća mapu u kojoj je ključ ocjena, a vrijednost lista svih studenata s tom ocjenom.
	 * @param records Lista svih studenata.
	 * @return Već opisana mapa.
	 */
	private static Map<Integer, List<StudentRecord>> razvrstajStudentePoOcjenama(List<StudentRecord> records) {
		return records.stream().collect(Collectors.groupingBy(StudentRecord::getGrade));
	}

	/**
	 * Metoda za upit koji vraća mapu kojoj je ključ određena ocjena, a vrijednost broj studenata s tom ocjenom.
	 * @param records Lista svih studenata.
	 * @return Već opisana mapa.
	 */
	private static Map<Integer, Integer> vratiBrojStudenataPoOcjenama(List<StudentRecord> records) {
		return records.stream()
				.collect(Collectors.toMap(StudentRecord::getGrade, s -> new Integer(1), (s1, s2) -> (s1 + s2)));
	}

	/**
	 * Metoda za upit koji vraća mapu kojoj je ključ boolean vrijednost koja označava je li položen predmet,
	 * a vrijednost lista svih studenata koji su položili odnosno nisu položili predmet.
	 * @param records Lista svih studenata.
	 * @return Već opisana mapa.
	 */
	private static Map<Boolean, List<StudentRecord>> razvrstajProlazPad(List<StudentRecord> records) {
		return records.stream().collect(Collectors.partitioningBy(s -> s.getGrade() > 1));
	}

}

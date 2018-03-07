package hr.fer.zemris.java.hw04.db;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw04.collections.SimpleHashtable;

/**
 * Razred koji predstavlja kvazi bazu podataka velikog broja StudentRecorda. Razred omogućava dvije metode
 * za jednostavne upite nad tom bazom.
 * @author Branko Sirotković
 *
 */
public class StudentDatabase {
	
	/**
	 * Kolekcija StudentRecorda u obliku HashMap-e što omogućava O(1) upite ukoliko poznajemo
	 * JMBAG studenta.
	 */
	private SimpleHashtable<String, StudentRecord> students;
	
	/**
	 * Kolekcija StudentRecord-a u obliku liste.
	 */
	private List<StudentRecord> studentsList;

	/**
	 * Konstruktor koji incijalizire obe svoje kolekcije s obzirom na primljenu listu Stringova
	 * u kojima se sadrži zapis nekog studenta.
	 * @param list Lista studenata u obliku Stringa.
	 * @throws ArrayIndexOutOfBoundsException Kada je lista primljenih Stringova u neispravnom obliku.
	 */
	public StudentDatabase(List<String> list){
		if(list == null){
			throw new IllegalArgumentException("Konstruktoru StudentDatabase predana je null lista.");
		}
		
		students = new SimpleHashtable<>(list.size());
		studentsList = new ArrayList<StudentRecord>();
		
		for (String string : list) {
			String[] studentInfo = string.split("\t");
			
			//Pripaziti na iznimku ArrayIndexOutOfBounds kada se koristi ovaj razred.
			StudentRecord student = new StudentRecord(studentInfo[0], studentInfo[1], studentInfo[2], Integer.valueOf(studentInfo[3]));
			students.put(student.getJmbag(), student);
			studentsList.add(student);
		}
	}
	
	/**
	 * Metoda koja vraća StudentRecord onog studenta čiji se JMBAG poklapa sa primljenim argumentom.
	 * @param jmbag JMBAG kojeg tražimo u našoj bazi podataka.
	 * @return StudentRecord koji ima traženi JMBAG.
	 */
	public StudentRecord forJMBAG(String jmbag){
		return students.get(jmbag);
	}
	
	/**
	 * Metoda koja vraća listu StudentRecord-a koji zadovoljavaju pravila primljenog filtera.
	 * @param filter IFilter koji filtrira StudentRecorde.
	 * @return Lista StudentRecord-a koji su zadovoljili uvjete.
	 */
	public List<StudentRecord> filter(IFilter filter){
		ArrayList<StudentRecord> list = new ArrayList<>();
		
		for (StudentRecord studentRecord : studentsList) {
			if(filter.accepts(studentRecord)){
				list.add(studentRecord);
			}
		}
		
		return list;
	}
	
}

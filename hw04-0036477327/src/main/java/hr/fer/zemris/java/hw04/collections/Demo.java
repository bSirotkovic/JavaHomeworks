package hr.fer.zemris.java.hw04.collections;

import java.util.Iterator;

/**
 * Demonstracijski razred za razred SimpleHashtable. Testira sve metode tog razreda.
 * @author Branko Sirotković
 *
 */
public class Demo {

	/**
	 * Metoda koja se pokreće kada se pokrene program.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args) {

		SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(3);

		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		examMarks.put("Branko", 1);
		examMarks.put("Branko", 2);
		examMarks.put("Stipe", 0);
		examMarks.put("Ivan", 10);
		examMarks.put("Stipe", 5);

		System.out.println(examMarks.toString()); // Treba ispisati 7 ljudi.

		System.out.println("Kristina's exam grade is: " + examMarks.get("Kristina")); // writes:
																						// 5
		System.out.println("Branko's exam grade is: " + examMarks.get("Branko")); // writes:
																					// 2
		System.out.println(examMarks.get("Niko")); // writes: null
		System.out.println(examMarks.containsValue(10)); // writes: true
		examMarks.remove("Branko");
		System.out.println("Branko's exam grade is: " + examMarks.get("Branko")); // writes:
																					// null
		System.out.println("Number of stored pairs: " + examMarks.size()); // writes:
																			// 6
		System.out.println(examMarks.containsKey("Brane")); // writes: false
		System.out.println(examMarks.containsKey("Stipe")); // writes: true
		System.out.println(examMarks.toString());

		// Normalan ispis
		for (SimpleHashtable.TableEntry<String, Integer> pair : examMarks) {
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
		}

		System.out.println();

		// Ukloni ivanu
		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Stipe")) {
				iter.remove(); // sam iterator kontrolirano uklanja trenutni
				// element
			}
		}

		System.out.println();

		// Kartezijev umnožak
		for (SimpleHashtable.TableEntry<String, Integer> pair1 : examMarks) {
			for (SimpleHashtable.TableEntry<String, Integer> pair2 : examMarks) {
				System.out.printf("(%s => %d) - (%s => %d)%n", pair1.getKey(), pair1.getValue(), pair2.getKey(),
						pair2.getValue());
			}
		}

		System.out.println();

		// Should throw IllegalStateException.
		// iter = examMarks.iterator();
		// while (iter.hasNext()) {
		// SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
		// if (pair.getKey().equals("Kristina")) {
		// iter.remove();
		// iter.remove();
		// }
		// }

		// Should throw ConcurentModificationException
		// iter = examMarks.iterator();
		// while (iter.hasNext()) {
		// SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
		// if (pair.getKey().equals("Kristina")) {
		// examMarks.remove("Kristina");
		// }
		// }

		iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
			iter.remove();
		}
		System.out.printf("Veličina: %d%n", examMarks.size());

	}

}

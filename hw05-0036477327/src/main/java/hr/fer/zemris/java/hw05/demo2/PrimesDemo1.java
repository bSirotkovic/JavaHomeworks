package hr.fer.zemris.java.hw05.demo2;

/**
 * Razred koji se koristi za demonstraciju funkcionalnosti iteratora razreda PrimesCollection.
 * @author Branko Sirotković
 *
 */
public class PrimesDemo1 {

	/**
	 * Metoda koja se izvodi pokretanjem ovog programa.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args) {
		PrimesCollection primesCollection = new PrimesCollection(15); // 5: how many of them
		
		for(Integer prime : primesCollection) {
			System.out.println("Got prime: "+prime);
		}
		
	}

}

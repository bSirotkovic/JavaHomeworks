package hr.fer.zemris.java.hw05.demo2;

/**
 * Razred koji se koristi za demonstraciju funkcionalnosti iteratora razreda PrimesCollection.
 * @author Branko Sirotković
 *
 */
public class PrimesDemo2 {

	/**
	 * Metoda koja se izvodi pokretanjem ovog programa.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args) {
		
		PrimesCollection primesCollection = new PrimesCollection(5);
		for(Integer prime : primesCollection) {
			for(Integer prime2 : primesCollection) {
				System.out.println("Got prime pair: "+prime+", "+prime2);
			}
		}
		
	}

}

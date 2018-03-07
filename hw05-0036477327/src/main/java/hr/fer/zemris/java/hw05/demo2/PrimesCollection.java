package hr.fer.zemris.java.hw05.demo2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Razred koji predstavlja kolekciju prvih nekoliko prostih brojeva. Razred omogućuje dohvaćanje iteratora po tom slijedu prostih brojeva,
 * a u konstruktoru mu se zadaje koliko prvih prostih brojeva se treba nalaziti u kolekciji.
 * @author Branko Sirotković
 *
 */
public class PrimesCollection implements Iterable<Integer>{

	/**
	 * Varijabla koja označava koliko prvih prostih brojeva se nalazi u kolekciji.
	 */
	private int n;
	
	/**
	 * Konstruktor koji je zadužen za primanje i postavljanje varijable koja označava
	 * koliko prostih brojeva će se nalaziti u kolekciji.
	 * @param n Koliko prostih brojeva će se nalaziti u kolekciji. Broj mora biti pozitivan.
	 */
	public PrimesCollection(int n){
		if(n < 1){
			throw new IllegalArgumentException("Broj koji se šalje konstruktoru PrimesCollection mora biti pozitivan broj.");
		}
		
		this.n = n;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new PrimeIterator();
	}
	
	/**
	 * Pomoćni razred razreda PrimesCollection koji služi kao konkretna implementacija razreda Iterator, koja 
	 * iterira ovom kolekcijom prostih brojeva.
	 * @author Branko Sirotković
	 *
	 */
	private class PrimeIterator implements Iterator<Integer>{

		/**
		 * Trenutni prosti broj. Odnosno posljednji vraćeni.
		 */
		private int currentPrime = 1;
		
		/**
		 * Brojač koliko prostih brojeva se u kolekciji prošlo.
		 */
		private int primeCounter = 0;
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return primeCounter < n;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Integer next() {
			if(!hasNext()){
				throw new NoSuchElementException("Iterator nema više prostih brojeva za predati.");
			}
			
			primeCounter++;
			return currentPrime = calculateNextPrime(currentPrime + 1);
		}

		/**
		 * Pomoćna metoda koja računa prvi prosti broj jednak ili veći od primljenog Integera.
		 * @param num Broj koji označava donju granicu prvog sljedečeg prostog broja.
		 * @return Prvi prosti broj jednak ili veći primljenom argumentu.
		 */
		private Integer calculateNextPrime(int num) {
			boolean notPrime = true;
			
			while(notPrime){
				notPrime = false;
				for(double i = 2, sqrt = Math.sqrt(num); i <= sqrt; i++){
					
					if(num % i == 0){
						notPrime = true;
						break;
					}
					
				}	
				num++;
			}
			return num - 1;
		}
		
		
	}
}

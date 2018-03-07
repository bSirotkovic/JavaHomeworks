package hr.fer.zemris.java.hw05.observer1;

/**
 * Konkretna implementacija razreda IntegerStorageObserver. Promatrač koji broji koliko puta se 
 * promijenila vrijednost IntegerSotrage-a.
 * @author Branko Sirotković
 *
 */
public class ChangeCounter implements IntegerStorageObserver {

	/**
	 * Brojač promjena vrijednosti.
	 */
	private int counter = 0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		counter++;
		System.out.println("Number of value changes since tracking: " + counter);
	}

}

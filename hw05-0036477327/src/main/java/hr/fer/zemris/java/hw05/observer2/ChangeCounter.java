package hr.fer.zemris.java.hw05.observer2;

/**
 * Konkretna implementacija sučelja IntegerStorageObserver. Ovaj promatrač ispisuje koja je ovo promjena
 * nad IntegerStorage-om kojeg promatra.
 * @author Branko Sirotković
 *
 */
public class ChangeCounter implements IntegerStorageObserver {

	/**
	 * Brojač promjena nad IntegerStorage.
	 */
	private int counter = 0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueChanged(IntegerStorageChange change) {
		counter++;
		System.out.println("Number of value changes since tracking: " + counter);
	}

}

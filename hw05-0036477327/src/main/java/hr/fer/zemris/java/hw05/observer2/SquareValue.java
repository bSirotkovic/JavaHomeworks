package hr.fer.zemris.java.hw05.observer2;

/**
 * Konkretna implementacija sučelja IntegerStorageObserver. Ovaj promatrač ispisuje kvadratnu 
 * vrijednost nove vrijednost IntegerStorage-a.
 * @author Branko Sirotković
 *
 */
public class SquareValue implements IntegerStorageObserver {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueChanged(IntegerStorageChange change) {
		int v = change.getNewValue();
		System.out.println("Provided new value: " + v + ", square is " + v*v);
	}

}

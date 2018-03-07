package hr.fer.zemris.java.hw05.observer1;

/**
 * Konkretna implementacija sučelja IntegerStorageObserver. Ovaj promatrač
 * ispisuje kvadratnu vrijednost nove vrijednosti IntegerSotrage-a.
 * @author Branko Sirotković
 *
 */
public class SquareValue implements IntegerStorageObserver {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		int v = istorage.getValue();
		System.out.println("Provided new value: " + v + ", square is " + v*v);
	}

}

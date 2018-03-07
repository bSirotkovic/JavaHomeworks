package hr.fer.zemris.java.hw05.observer2;

/**
 * Konkretna implementacija sučelja IntegerStorageObserver. Ovaj promatrač ispisuje dvostruku vrijednost
 * nove vrijednost IntegerStorage-a svaki put kada se promijeni.
 * @author Branko Sirotković
 *
 */
public class DoubleValue implements IntegerStorageObserver {

	/**
	 * Broj promjena za koje je ovaj promatrač aktivan.
	 */
	private int numOfTimes;
	
	/**
	 * Konstruktor koji inicijalizira varijablu numOfTimes.
	 * @param n Broj promjena IntegerStorage-a za koje će ovaj promatrač biti aktivan.
	 */
	public DoubleValue(int n){
		if(n < 0){
			throw new IllegalArgumentException("Argument koji se šalje konstruktoru DoubleValue-a treba biti nenegativan broj.");
		}
		numOfTimes = n;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueChanged(IntegerStorageChange change) {
		if(numOfTimes == 0){
			change.getIntStorage().removeObserver(this);
			return;
		}
		
		numOfTimes--;
		System.out.println("Double value: " + change.getNewValue() * 2);
	}

}

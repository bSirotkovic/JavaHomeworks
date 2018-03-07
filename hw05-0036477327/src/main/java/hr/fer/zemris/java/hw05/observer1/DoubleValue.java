package hr.fer.zemris.java.hw05.observer1;

/**
 * Konkretna implementacija razreda IntegerStorageObserver. Ovo je promatrač koji
 * ispiše dvostruko veću vrijednost nove vrijednosti IntegerStorage-a.
 * @author Branko Sirotković
 *
 */
public class DoubleValue implements IntegerStorageObserver {

	/**
	 * Broj puta promjena za koje je ovaj promatrač aktivan.
	 */
	private int numOfTimes;
	
	/**
	 * Konstruktor koji inicijalizira varijablu numOfTimes.
	 * @param n Broj promjena za koje je ovaj promatrač aktivan.
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
	public void valueChanged(IntegerStorage istorage) {
		if(numOfTimes == 0){
			istorage.removeObserver(this);
			return;
		}
		
		numOfTimes--;
		System.out.println("Double value: " + istorage.getValue() * 2);
	}

}

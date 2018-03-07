package hr.fer.zemris.java.hw05.observer1;

/**
 * Sučelje koje definira sve potrebne metode za jedan razred koji se želi ponašati kao
 * promatrač razred IntegerStorage.
 * @author Branko Sirotković
 *
 */
public interface IntegerStorageObserver {

	/**
	 * Metoda koja se poziva nad svim promatračima kada nastupi promjena vrijednosti onoga što promatraju.
	 * @param istorage IntegerStorage koji se promatra.
	 */
	public void valueChanged(IntegerStorage istorage);
}

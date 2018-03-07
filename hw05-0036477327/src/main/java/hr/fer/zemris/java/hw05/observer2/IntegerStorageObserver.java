package hr.fer.zemris.java.hw05.observer2;

/**
 * Sučelje koje definira sve potrebne metode koje mora implementirati razred koji želi
 * vršiti funkciju promatrača nad IntegerStorage.
 * @author Branko Sirotković
 *
 */
public interface IntegerStorageObserver {

	/**
	 * Metoda koja se poziva unutar IntegerStorage-a kada nastupi promjena njegove vrijednosti.
	 * @param intStorageChange Instanca razreda koji je zaslužan za pamćenje detalja izmjene. 
	 */
	public void valueChanged(IntegerStorageChange intStorageChange);
}

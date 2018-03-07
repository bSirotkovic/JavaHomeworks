package hr.fer.zemris.java.hw05.observer2;

/**
 * Razred koji prati promjenu jednog IntegerStorage. Pamti staru i novu vrijednost.
 * @author Branko Sirotković.
 *
 */
public class IntegerStorageChange {

	/**
	 * Stara vrijednost IntegerStorage-a.
	 */
	private int oldValue;
	
	/**
	 * Nova vrijednost IntegerStorage-a.
	 */
	private int newValue;
	
	/**
	 * IntegerStorage nad kojim se prate izmjene.
	 */
	private IntegerStorage intStorage;
	
	/**
	 * Konstruktor koji inicijalizira sve varijable ovog razreda.
	 * @param intStorage IntegerStorage nad kojim se prate izmjene.
	 * @param oldValue Stara vrijednost IntegerStorage-a.
	 * @param newValue Nova vrijednost IntegerStorage-a.
	 */
	public IntegerStorageChange(IntegerStorage intStorage, int oldValue, int newValue){
		if(intStorage == null){
			throw new IllegalArgumentException("IntegerStorageChange mora primit IntegerStorage u konstruktoru koji nije null.");
		}
		
		this.intStorage = intStorage;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Getter za staru vrijednost.
	 * @return Stara vrijednost IntegerStorage-a.
	 */
	public int getOldValue() {
		return oldValue;
	}

	/**
	 * Getter za novu vrijednost.
	 * @return Nova vrijednost IntegerStorage-a.
	 */
	public int getNewValue() {
		return newValue;
	}

	/**
	 * Getter za instancu IntegerStorage-a kojeg se prati.
	 * @return IntegerStorage.
	 */
	public IntegerStorage getIntStorage() {
		return intStorage;
	}
	
	
}

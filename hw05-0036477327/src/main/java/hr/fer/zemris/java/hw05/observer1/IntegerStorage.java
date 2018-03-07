package hr.fer.zemris.java.hw05.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Razred koji predstavlja jedan Wrapper za int vrijednost. Razred omogućava promijenu vrijednosti, te 
 * i funkcionalnost pretplaćivanja raznih promatraća na ovaj razred. Promatraći moraju implementirati sučelje
 * IntegerStorageObeserver.
 * @author Branko Sirotković
 *
 */
public class IntegerStorage {

	/**
	 * Sama vrijednost razreda.
	 */
	private int value;
	
	/**
	 * Lista pretplaćenih promatrača.
	 */
	private List<IntegerStorageObserver> observers;
	
	/**
	 * Lista promatraća koja moraju biti obrisani prije sljedeće obavijesti svim promatračima.
	 */
	private List<IntegerStorageObserver> toRemove;

	/**
	 * Konstruktor koji prima vrijednost razreda.
	 * @param initialValue Vrijednost IntegerStorage-a.
	 */
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		observers = new ArrayList<IntegerStorageObserver>();
		toRemove = new ArrayList<IntegerStorageObserver>();
	}

	/**
	 * Metoda koja pretplaćuje promatrač primljen kao argument na ovaj IntegerStorage.
	 * @param observer Promatrač koji se pretplaćuje.
	 */
	public void addObserver(IntegerStorageObserver observer) {
		if(observer == null){
			throw new IllegalArgumentException("Ne može se dodati null promatrač.");
		}
		
		if(!observers.contains(observer)){
			observers.add(observer);			
		}
	}

	/**
	 * Metoda koja briše primljenog promatrača iz pretplatnika.
	 * @param observer Promatrač koji više neće biti pretplaćen.
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		if(observer == null) return;
		
		toRemove.add(observer);
	}

	/**
	 * Metoda koja briše sve pretplatnike ovog IntegerStorage-a. 
	 */
	public void clearObservers() {
		for (IntegerStorageObserver obs : observers) {
			toRemove.add(obs);
		}
	}

	/**
	 * Getter za vrijednost.
	 * @return value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter za value. Metoda o promjeni obavještava sve pretplaćene promatrače.
	 * @param value Vrijednost IntegerStorage-a.
	 */
	public void setValue(int value) {
		
		if (this.value != value) {
		
			this.value = value;
			
			for (IntegerStorageObserver observer : toRemove) {
				observers.remove(observer);
			}
			toRemove.clear();
			
			for (IntegerStorageObserver observer : observers) {
				observer.valueChanged(this);
			}
			
		}
	}

}

package hr.fer.zemris.java.hw05.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * Razred koji predstavlja jedan Integer Wrapper. Te omogućuje promjena vrijednosti te pretplačivanje
 * raznih promatrača na te promjene.
 * @author Branko Sirotković
 *
 */
public class IntegerStorage {

	/**
	 * Trenutna vrijednost IntegerStorage-a.
	 */
	private int value;
	
	/**
	 * Lista svih pretplaćenih promatrača na ovaj IntegerStorage.
	 */
	private List<IntegerStorageObserver> observers;
	
	/**
	 * Pomoćna lista koja pamti sve promatrača koji žele maknuti pretplatu prije sljedeće promjene.
	 */
	private List<IntegerStorageObserver> toRemove;

	/**
	 * Konstruktor koji incijalizira sve varijable IntegerStorage-a.
	 * @param initialValue Početna vrijednost.
	 */
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		observers = new ArrayList<IntegerStorageObserver>();
		toRemove = new ArrayList<IntegerStorageObserver>();
	}

	/**
	 * Metoda koja pretplaćuje novog promatrača na IntegerStorage.
	 * @param observer Promatrač koji se želi pretplatiti.
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
	 * Metoda koja briše pretplatu nekog promatrača.
	 * @param observer Promatrač koji želi obrisati pretplatu.
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		if(observer == null) return;
		
		toRemove.add(observer);
	}

	/**
	 * Metoda koja briše pretplatu svih pretplaćenih promatrača.
	 */
	public void clearObservers() {
		for (IntegerStorageObserver obs : observers) {
			toRemove.add(obs);
		}
	}

	/**
	 * Getter za value.
	 * @return value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter za value koji ujedno i poziva metode svih promatrača da je nastupila promjena vrijednosti.
	 * @param value Nova vrijednost.
	 */
	public void setValue(int value) {
	
		if (this.value != value) {
			IntegerStorageChange change = new IntegerStorageChange(this, this.value, value);
			
			this.value = value;
			
			for (IntegerStorageObserver observer : toRemove) {
				observers.remove(observer);
			}
			toRemove.clear();
			
			for (IntegerStorageObserver observer : observers) {
				observer.valueChanged(change);
			}
			
		}
	}

}

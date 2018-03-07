package hr.fer.zemris.java.hw04.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Razred koji predstavlja kolekcija koja je izvedena kao tablica raspršenog adresiranja. Tablica sprema uređene parove
 * (Ključ, Vrijednost). Ključ ne smije dok vrijednost smije biti null.
 * @author Branko Sirotković
 * @param <K> Ključ u uređenom paru kojeg ova kolekcija sprema.
 * @param <V> Vrijednost u uređenom paru kojeg ova kolekcija sprema.
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{

	/**
	 * Pretpostavljeni broj slotova u tablici.
	 */
	private static final int DEFAULT_SLOT_SIZE = 16;
	
	/**
	 * Postotak popunjenosti koji kada se dostigne, tablica udvostručuje svoj broj slotova.
	 */
	private static final double MAX_FILLED_PERCENTAGE = 0.75;
	
	/**
	 * Broj uređenih parova u kolekciji.
	 */
	private int size;
	
	/**
	 * Polje slotova koje zapravo sprema sve elemente tablice.
	 */
	private TableEntry<K, V>[] table;
	
	/**
	 * Broj promjena napravljen nad tablicom.
	 */
	private int modificationCount;
	
	/**
	 * Konstruktor koji postavlja broj slotova na DEFAULT_SLOT_SIZE
	 */
	public SimpleHashtable(){
		this(DEFAULT_SLOT_SIZE);
	}
	
	/**
	 * Konstruktor koji postavlja broj slotova prvu veću ili jednaku potenciju broja 2 od broja koji je poslan u argumentu.
	 * @param slotNum Broj slotova koji želimo.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int slotNum){
		if(slotNum < 1){
			throw new IllegalArgumentException("Broj slotova predan konstruktoru SimpleHashTable treba biti barem 1, a jednaka je " + slotNum);
		}
		
		int tempSlotNum;
		for(tempSlotNum = 1; tempSlotNum < slotNum; tempSlotNum *= 2);
		table = (TableEntry<K, V>[]) new TableEntry[tempSlotNum];
		
		size = 0;
	}
	
	/**
	 * Getter za varijablu size.
	 * @return size.
	 */
	public int size(){
		return size;
	}

	/**
	 * Metoda koja dodaje uređeni par u tablicu, ukoliko se doda neki par s ključem koji već postoji u tablici, tada se value samo ažurira.
	 * @param key Ključ uređenog para.
	 * @param value Vrijednost uređenog para.
	 */
	public void put(K key, V value){
		if(key == null){
			throw new IllegalArgumentException("Ne može se u SimpleHashTable dodati uređeni par s ključem null.");
		}
		
		int position = Math.abs(key.hashCode()) % table.length;
		TableEntry<K, V> mySlot = table[position];
		
		if(mySlot == null){
			table[position] = new TableEntry<>(key, value, null);
			incrementSize();
			return;
		}
		
		TableEntry<K, V> previous = null;
		
		while(mySlot != null){
			if(key.equals(mySlot.getKey())){
				mySlot.setValue(value);
				return;
			}
			
			previous = mySlot;
			mySlot = mySlot.next;
		}
		
		previous.next = new TableEntry<>(key, value, null);
		incrementSize();
	}
	
	/**
	 * Metoda koja daje vrijednost onog uređenog para koji ima ključ jednak onome primljenom u argumentu. Vraća null, ako nema toga ključa.
	 * @param key Ključ uređenog para.
	 * @return Vrijednost uređenog para.
	 */
	public V get(Object key){
		if(key == null) return null;
		
		TableEntry<K, V> mySlot = table[ Math.abs(key.hashCode()) % table.length ];
		
		while(mySlot != null){
			if(key.equals(mySlot.getKey())){
				return mySlot.getValue();
			}
			
			mySlot = mySlot.next;
		}
		
		return null;
	}
	
	/**
	 * Metoda koja miče uređeni par iz kolekcije sa zadanim ključem.
	 * @param key Ključ para kojeg mičemo.
	 */
	public void remove(Object key) {
		if (key == null)
			return;
		
		int position = Math.abs(key.hashCode()) % table.length;
		TableEntry<K, V> mySlot = table[position];

		//Prazan slot.
		if(mySlot == null) return;
		
		//Brisanje prvog.
		if(mySlot.getKey().equals(key)){
			table[position] = mySlot.next;
			size--;
			modificationCount++;
			return;
		}
		
		//Inače.
		TableEntry<K, V> previous = null;
		while(mySlot != null){
			if(mySlot.getKey().equals(key)){
				previous.next = mySlot.next;
				size--;
				modificationCount++;
				return;
			}
			
			previous = mySlot;
			mySlot = mySlot.next;
		}
	}
	
	/**
	 * Metoda koja provjerava sadrži li tablica uređeni par sa zadanim ključem.
	 * @param key Ključ uređenog para.
	 * @return boolean sadrži li ga.
	 */
	public boolean containsKey(Object key) {
		if (key == null) return false;

		TableEntry<K, V> mySlot = table[ Math.abs(key.hashCode()) % table.length ];

		while(mySlot != null){
			if(mySlot.getKey().equals(key)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Metoda koja provjerava sadrži li tablica uređeni par sa zadanom vrijednošću.
	 * @param value Vrijednost uređenog para.
	 * @return boolean sadrži li je.
	 */
	public boolean containsValue(Object value){
		
		for(int i = 0, n = table.length; i < n; i++){
			TableEntry<K, V> tempSlot = table[i];
			
			while(tempSlot != null){
				if(tempSlot.getValue().equals(value)){
					return true;
				}
				tempSlot = tempSlot.next;
			}
		}
		
		return false;
	}
	
	/**
	 * Metoda koja provjerava je li tablica prazna.
	 * @return boolean je li tablica prazna.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		if(isEmpty()) return "[]";
		
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0, n = table.length; i < n; i++){
			TableEntry<K, V> tempSlot = table[i];
			
			while(tempSlot != null){
				sb.append(tempSlot.getKey() + "=" + tempSlot.getValue() + ", ");
				tempSlot = tempSlot.next;
			}
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Metoda koja udvostručuje broj slotova tablice ukoliko se pređe postotak popunjenosti jednak MAX_FILLED_PERCENTAGE.
	 */
	@SuppressWarnings("unchecked")
	private void incrementSize(){
		size++;
		modificationCount++;
		if(size < MAX_FILLED_PERCENTAGE * table.length) return;
		
		//Sve parove iz tablice prvo prebacujemo u polje.
		TableEntry<K, V>[] arrayOfEntries = new TableEntry[size];
		int counter = 0;
		
		for(int i = 0, n = table.length; i < n; i++){
			TableEntry<K, V> tempSlot = table[i];
			
			while(tempSlot != null){
				arrayOfEntries[counter] = tempSlot;
				counter++;
				tempSlot = tempSlot.next;
			}
		}
		
		clear(); //zbog ovoga će se de facto modification count uvećati za 2 (jednom na početku ove metode), ali funkcionalnost ostaje ista.
		table = (TableEntry<K, V>[]) new TableEntry[2* table.length]; //Double check ovo.
		
		for (TableEntry<K, V> tableEntry : arrayOfEntries) {
			put(tableEntry.getKey(), tableEntry.getValue());
		}
		
	}
	
	/**
	 * Metoda koja u potpunosti čisti tablicu i čini je praznom.
	 */
	private void clear() {
		for (int i = 0, n = table.length; i < n; i++) {
			table[i] = null;
		}
		size = 0;
		modificationCount++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}
	
	/**
	 * Razred s kojim modeliramo jedan slot razreda SimpleHashTable
	 * @author Branko Sirotković
	 * @param <K> Ključ jednog uređenog para koji se posprema SimpleHashTable
	 * @param <V> Vrijednost jednog uređenog para koji se posprema u SimpleHashTable
	 */
	public static class TableEntry<K, V> {
		
		/**
		 * Ključ jednog uređenog para koji se posprema SimpleHashTable
		 */
		private K key;

		/**
		 * Vrijednost jednog uređenog para koji se posprema u SimpleHashTable
		 */
		private V value;

		/**
		 * Pokazivač na sljedeći uređeni par u pretincu.
		 */
		private TableEntry<K, V> next;

		/**
		 * Konsturktor koji prima dva argumenta, ključ i vrijednost uređenog para.
		 * @param key Ključ uređenog para kojeg stvaramo.
		 * @param value Vrijednost uređenog para kojeg stvaramo.
		 * @param next Sljedeći entry u istom slotu.
		 */
		public TableEntry(K key, V value, TableEntry<K, V> next) {
			if (key == null) {
				throw new IllegalArgumentException("Ne može se stvoriti instanca TableEntry-a sa vrijednosti null.");
			}

			this.key = key;
			this.value = value;
			this.next = next;
		}

		/**
		 * Getter za value.
		 * @return Vrijednost ovog uređenog para.
		 */
		public V getValue() {
			return value;
		}

		/**
		 * Setter za value.
		 * @param value Nova vrijednost varijable value.
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * Getter za ključ.
		 * @return Ključ ovog uređenog para.
		 */
		public K getKey() {
			return key;
		}

	}
	
	/**
	 * Implementacija razreda Iterator za konkretnu kolekciju. Podržana je operacija remove().
	 * @author Branko Sirotković
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
		/**
		 * Redni broj elementa na kojem se nalazi iterator.
		 */
		private int currentNum;
		
		/**
		 * Interni brojač promjena u tablici kojih je on svjestan.
		 */
		private int internalModificationCount;
		
		/**
		 * Trenutni slot na kojem se nalazi iterator.
		 */
		private TableEntry<K,V> slot;
		
		/**
		 * Prethodni slot na kojem se nalazio iterator.
		 */
		private TableEntry<K,V> previousSlot;
		
		/**
		 * Redni broj trenutnog slota.
		 */
		private int currentSlot;
		
		/**
		 * Zastavica koja regulira da se ne pozove dvaput metode remove bez da je pozvana metoda next.
		 */
		private boolean canRemove;
		
		/**
		 * {@inheritDoc}
		 */
		public IteratorImpl(){
			internalModificationCount = modificationCount;
			slot = table[0];
		}
		
		/**
		 * {@inheritDoc}
		 */
		public boolean hasNext() {
			if (internalModificationCount != modificationCount) {
				throw new ConcurrentModificationException("Rađene su promjene na SimpleHashtable tijekom iteracije.");
			}
			return currentNum < size;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public SimpleHashtable.TableEntry<K,V> next() {
			if(!hasNext()){
				throw new NoSuchElementException("Nema više elemenata u SimpleHashtable.");
			}
			
			//canRemove = true;
			
			if (slot == null) { // ako se nalazimo na kraju slota ili u praznom slotu.
				
				if(table[currentSlot] != null && !canRemove) { 	//polu hack. Ako se maloprije obrisao prvi iz slota, varijabla slot će biti null
																//a da još nismo gotovi sa slotom. Zato ne pomičemo slot. Znam da glupo izgleda prazan if. Ali je ovako puno lakše za shvatit kad se čita.
					 
				} else {										//inače pomiči slot kako treba.
					currentSlot++;
					while (table[currentSlot] == null) {
						currentSlot++;
					}
				}
				
				previousSlot = slot;							//te obavi što se mora
				slot = table[currentSlot];
			} else {											//Ako smo usred slota
				previousSlot = slot;
				slot = slot.next;
				if (slot == null) { 							// ako smo došli na kraj slota,
					return this.next();
				}
			}
			
			canRemove = true; //ono što se radi u svim slučajevima
			currentNum++;
			return slot;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public void remove() {
			if(!canRemove){
				throw new IllegalStateException("Dvaput se pozvala metoda remove(), bez da se pozvala metoda next().");
			}
			
			if (internalModificationCount != modificationCount) {
				throw new ConcurrentModificationException("Rađene su promjene na SimpleHashtable tijekom iteracije.");
			}
			
			canRemove = false;
			internalModificationCount++;
			SimpleHashtable.this.remove(slot.getKey());
			slot = previousSlot;
			currentNum--;
		}
	}

}



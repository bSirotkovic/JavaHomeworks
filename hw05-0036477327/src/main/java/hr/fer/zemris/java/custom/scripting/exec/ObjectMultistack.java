package hr.fer.zemris.java.custom.scripting.exec;

import java.util.HashMap;
import java.util.Map;

/**
 * Razred koji predstavlja kolekciju različitih stogova kojima se pristupa po vrijednost ključa.
 * Ključ je tipa String.
 * @author Branko Sirotković
 *
 */
public class ObjectMultistack {
	
	/**
	 * Interna mapa stogova.
	 */
	private Map<String, MultistackEntry> map;
	
	/**
	 * Konstruktor koji inicijalizira mapu.
	 */
	public ObjectMultistack(){
		map = new HashMap<>();
	}

	/**
	 * Metoda koja dodaje zadanu vrijednost ValueWrapper u njen odgovarajući stog koji je
	 * definiran ključem name.
	 * @param name Ključ stoga.
	 * @param valueWrapper Vrijednost koja se dodaje na stog.
	 */
	public void push(String name, ValueWrapper valueWrapper) {
		if(name == null) return;
		
		if(isEmpty(name)){
			map.put(name, new MultistackEntry(valueWrapper, null));
			return;
		}
		
		MultistackEntry tempEntry = map.remove(name);
		map.put(name, new MultistackEntry(valueWrapper, tempEntry));
	}
	
	/**
	 * Metoda koja vraća zadnju vrijednost sa stoga koji je definiran ključem name i miče tu vrijednost sa stoga.
	 * @param name Identifikator stoga.
	 * @return ValueWrapper sa stoga.
	 */
	public ValueWrapper pop(String name) {
		if(name == null || isEmpty(name)){
			throw new RuntimeException();
		}
		
		MultistackEntry tempEntry = map.remove(name);
		if(tempEntry.next != null){			
			map.put(name, tempEntry.next);
		}
		
		return tempEntry.getValue();
	}
	
	/**
	 * Metoda koja vraća zadnju vrijednost sa stoga koji je definiran ključem name.
	 * @param name Identifikator stoga.
	 * @return ValueWrapper sa stoga.
	 */
	public ValueWrapper peek(String name) {
		if(name == null || isEmpty(name)){
			throw new RuntimeException();
		}
		
		return map.get(name).getValue();
	}
	
	/**
	 * Metoda koja vraća boolean je li stoga sa ovim ključem prazan.
	 * @param name Identifikator stoga.
	 * @return boolean je li taj stog prazan.
	 */
	public boolean isEmpty(String name) {
		return !map.containsKey(name);
	}
	
	/**
	 * Pomoćni razred koji definira jedan čvor u listi koju ObjectMultistack koristi kao stog.
	 * @author Branko Sirotković
	 *
	 */
	private static class MultistackEntry{
		
		/**
		 * Sljedeći čvor u listi.
		 */
		private MultistackEntry next;
		
		/**
		 * Vrijednost čvora.
		 */
		private ValueWrapper value;
		
		/**
		 * Konstruktor koji inicijalizira sve varijable ovog razreda.
		 * @param value Vrijednost čvora.
		 * @param next Sljedeći čvor u list.
		 */
		public MultistackEntry(ValueWrapper value, MultistackEntry next){
			this.value = value;
			this.next = next;
		}
		
		/**
		 * Getter za vrijednost.
		 * @return Vrijednost čvora.
		 */
		public ValueWrapper getValue(){
			return value;
		}
	}
}

package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Razred koji predstavlja konstrukcijsku jedinicu razreda Node. Vršni razred za različite tipove vrijednosti koje
 * se mogu spremati unutar razreda Element, ovaj razred nije implementiran, služi kao sučelje.
 * @author Branko Sirotković
 *
 */
public class Element {

	/**
	 * Metoda koja vraća glavnu vrijednost instance ovog razreda. U vršnom razredu Element je implementirana tako da vraća
	 * prazan String.
	 * @return Value razreda u String formatu, u slučaju razreda Element prazan String.
	 */
	public String asText(){
		return "";
	}
}

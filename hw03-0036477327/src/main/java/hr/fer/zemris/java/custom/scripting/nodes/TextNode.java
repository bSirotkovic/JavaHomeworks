package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * Čvor koji predstavlja odlomak teksta van TAG-a u dokumentu koji se želi prikazati strukturirano sa čvorovima.
 * @author Branko Sirotković
 *
 */
public class TextNode extends Node{

	/**
	 * Tekst kojeg čvor predstavlja.
	 */
	private String text;
	
	/**
	 * Konstruktor koji postavlja varijablu text. Text ne može biti null.
	 * @param text
	 */
	public TextNode(String text){
		if(text == null){
			throw new IllegalArgumentException("Poslani parametar text ne može biti null");
		}
		this.text = text;
	}
	
	/**
	 * Getter za varijablu text.
	 * @return text.
	 */
	public String getText(){
		return text;
	}
}

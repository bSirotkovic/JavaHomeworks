package hr.fer.zemris.java.hw06.shell;

/**
 * Iznimka koja nastaje prilikom rada u MyShellu.
 * @author Branko
 *
 */
public class ShellIOException extends RuntimeException {

	/**
	 * Serijalizirana verzija iznimke.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Defaultni konstruktor.
	 */
	public ShellIOException(){
		super();
	}
	
	/**
	 * Konstruktor koji postavlja poruku iznimke.
	 * @param message Poruka o iznimci.
	 */
	public ShellIOException(String message){
		super(message);
	}
}

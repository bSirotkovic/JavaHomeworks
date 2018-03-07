package hr.fer.zemris.java.hw06.shell;
/**
 * Enumeracija za moguće statuse terminala.
 * 
 * @author Branko Sirotković
 */
public enum ShellStatus {

	/**
	 * Status terminala kada treba nastaviti prihvačati korisnikove naredbe.
	 */
	CONTINUE,

	/**
	 * Status terminala kada treba zaustaviti svoj rad.
	 */
	TERMINATE;
}


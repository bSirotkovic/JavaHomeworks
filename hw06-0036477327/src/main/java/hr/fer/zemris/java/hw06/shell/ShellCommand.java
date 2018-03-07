package hr.fer.zemris.java.hw06.shell;

import java.util.List;

/**
 * Sučelje za naredbe terminala. Definira osnovne metode koje svaka naredba mora
 * implementirati.
 * 
 * @author Branko Sirotković
 */
public interface ShellCommand {

	/**
	 * Metoda koja poziva izvršavanje naredbe i postizanje njene funkcije.
	 * 
	 * @param env
	 *            Okruženje u kojem terminal djeluje.
	 * @param arguments
	 *            Argumenti naredbe terminala.
	 * @return Status terminala, treba li terminal nastaviti primati korisnikove
	 *         naredbe.
	 */
	ShellStatus executeCommand(Environment env, String arguments);

	/**
	 * Metoda koja dohvaća ime naredbe.
	 * 
	 * @return Ime naredbe
	 */
	String getCommandName();

	/**
	 * Metoda koja dohvaća opis naredbe.
	 * 
	 * @return Opis naredbe.
	 */
	List<String> getCommandDescription();
}


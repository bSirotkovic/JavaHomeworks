package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

/**
 * Javno sučelje koje modelira sučelje u kojem se izvršava Shell.
 * 
 * @author Branko Sirotković
 */
public interface Environment {

	/**
	 * Metoda koja učitava redak sa standardnog ulaza koji predstavlja sučelje
	 * Shell-a.
	 * 
	 * @return String redak.
	 */
	String readLine() throws ShellIOException;

	/**
	 * Metoda koja ispisuje redak na standardni izlaz.
	 * 
	 * @param text
	 *            Tekst koji se ispisuje.
	 */
	void write(String text) throws ShellIOException;

	/**
	 * Metoda koja delegira svoj posao metodi write i prebacuje naredni ispis u
	 * novi redak.
	 * 
	 * @param text
	 *            Tekst koji se ispisuje.
	 */
	void writeln(String text) throws ShellIOException;

	/**
	 * Metoda vraća kolekciju naredbi Shell-a po kojoj se može iterirati.
	 * 
	 * @return Iterabilna kolekcija naredbi.
	 */
	SortedMap<String, ShellCommand> commands();

	/**
	 * Getter za MultilineSymbol.
	 * 
	 * @return Trenutni Multiline simbol.
	 */
	Character getMultilineSymbol();

	/**
	 * Setter za MultilineSymbol.
	 * 
	 * @param symbol
	 *            Simbol na kojeg se postavlja multiline simbol.
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * Getter za PromptSymbol.
	 * 
	 * @return Trenutni Prompt simbol.
	 */
	Character getPromptSymbol();

	/**
	 * Setter za PromptSymbol
	 * 
	 * @param symbol
	 *            Simbol na kojeg se postavlja prompt simbol.
	 */
	void setPromptSymbol(Character symbol);

	/**
	 * Getter za MorelinesSymbol.
	 * 
	 * @return Trenutni Morelines simbol.
	 */
	Character getMorelinesSymbol();

	/**
	 * Setter za MorelinesSymbol
	 * 
	 * @param symbol
	 *            Simbol na kojeg se postavlja Morelines simbol.
	 */
	void setMorelinesSymbol(Character symbol);

	/**
	 * Metoda koja vraća instancu naredbe koja ima ime zadanu u argumentu
	 * metode.
	 * 
	 * @param name
	 *            Ime naredbe čija se instanca vrača.
	 * @return Instanca naredbe sa zadanim imenom.
	 */
	ShellCommand getCommand(String name);
}

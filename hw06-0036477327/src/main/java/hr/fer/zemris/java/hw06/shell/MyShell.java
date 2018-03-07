package hr.fer.zemris.java.hw06.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.util.TreeMap;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.commands.CatCommand;
import hr.fer.zemris.java.hw06.shell.commands.CharsetsCommand;
import hr.fer.zemris.java.hw06.shell.commands.CopyCommand;
import hr.fer.zemris.java.hw06.shell.commands.ExitCommand;
import hr.fer.zemris.java.hw06.shell.commands.HelpCommand;
import hr.fer.zemris.java.hw06.shell.commands.HexDumpCommand;
import hr.fer.zemris.java.hw06.shell.commands.LsCommand;
import hr.fer.zemris.java.hw06.shell.commands.MkDirCommand;
import hr.fer.zemris.java.hw06.shell.commands.SymbolCommand;
import hr.fer.zemris.java.hw06.shell.commands.TreeCommand;

/**
 * Razred koji predstavlja jednostavan terminal.
 * 
 * @author Branko Sirotković
 */
public class MyShell {
	/**
	 * Interna kolekcija svih postojećih naredbi terminala.
	 */
	private static SortedMap<String, ShellCommand> commands;

	/**
	 * Statički ugnježđeni razred koji je konkretna implementacija sučelja
	 * Environment.
	 * 
	 * @author Branko Sirotković
	 * @version 1.0
	 */
	private static class EnvironmentImpl implements Environment {
		/**
		 * Simbol za ispis na početku svake linije terminala.
		 */
		private Character promptSymbol = '>';

		/**
		 * Simbol kojim korisnik obavještava terminal da će nastaviti unos iste
		 * komande.
		 */
		private Character moreLinesSymbol = '\\';

		/**
		 * Simbol kojim terminal potvrđuje korisnikov unos kroz više redaka.
		 */
		private Character multiLinesSymbol = '|';

		/**
		 * Ulaz s kojeg terminal čita.
		 */
		private BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

		/**
		 * Izlaz na kojeg terminal ispisuje.
		 */
		private BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String readLine() {
			try {
				return in.readLine();
			} catch (IOException e) {
				System.out.println("Communication with shell interface to read and write failed.");
				System.exit(1);
			}
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void write(String text) {
			try {
				out.write(text);
				out.flush();
			} catch (IOException e) {
				System.out.println("Communication with shell interface to read and write failed.");
				System.exit(1);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void writeln(String text) {
			write(text + "\n");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SortedMap<String, ShellCommand> commands() {
			return commands;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ShellCommand getCommand(String name) {
			return commands.get(name);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Character getMultilineSymbol() {
			return multiLinesSymbol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setMultilineSymbol(Character symbol) {
			multiLinesSymbol = symbol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Character getPromptSymbol() {
			return promptSymbol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setPromptSymbol(Character symbol) {
			promptSymbol = symbol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Character getMorelinesSymbol() {
			return moreLinesSymbol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setMorelinesSymbol(Character symbol) {
			moreLinesSymbol = symbol;
		}

	}

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * 
	 * @param args
	 *            Argumenti komandne linije. Program ne očekuje i ne koristi ove
	 *            argumente.
	 * @return 
	 */
	public static void main(String[] args) {
		EnvironmentImpl environment = new EnvironmentImpl();
		commands = new TreeMap<String, ShellCommand>();
		commands.put("help", new HelpCommand());
		commands.put("exit", new ExitCommand());
		commands.put("symbol", new SymbolCommand());
		commands.put("charsets", new CharsetsCommand());
		commands.put("cat", new CatCommand());
		commands.put("ls", new LsCommand());
		commands.put("tree", new TreeCommand());
		commands.put("copy", new CopyCommand());
		commands.put("mkdir", new MkDirCommand());
		commands.put("hexdump", new HexDumpCommand());

		Environment myEnvironment = new EnvironmentImpl();
		myEnvironment.writeln("Welcome to MyShell v 1.0");
		ShellStatus status = ShellStatus.CONTINUE;

		try {
			while (status != ShellStatus.TERMINATE) {
				String line = readLineOrLines(myEnvironment);
				int index = line.indexOf(" ");
				String commandName;
				String arguments;
				if (index == -1) {
					commandName = line;
					arguments = null;
				} else {
					commandName = line.substring(0, index);
					arguments = line.substring(index + 1);
				}
				ShellCommand command = commands.get(commandName.toLowerCase());
				status = command.executeCommand(environment, arguments);
			}
		} catch (ShellIOException ex) {
			System.out.println("Communication with shell interface to read and write failed.");
			System.exit(1);
		} catch (Exception ex){
			System.out.println("Some other error occured.");
			System.exit(1);
		}
	}

	/**
	 * Pomoćna metoda koja omogućava unos korisniku u terminal. Omogućava
	 * svojstvo terminala da se naredbe unose u jednoj ili više linija.
	 * 
	 * @param myEnvironment
	 *            Okruženje u kojem terminal djeluje.
	 * @return String teksta kojeg je unio korisnik.
	 * @throws IOException
	 *             U slučaju neuspjelog čitanja sa standardnog ulaza.
	 */
	private static String readLineOrLines(Environment myEnvironment) throws IOException {
		myEnvironment.write(Character.toString(myEnvironment.getPromptSymbol()));
		String line = myEnvironment.readLine();
		while (true) {
			if (line == null || line.charAt(line.length() - 1) != myEnvironment.getMorelinesSymbol()) {
				break;
			}
			myEnvironment.write(Character.toString(myEnvironment.getMultilineSymbol()) + " ");
			line += " " + myEnvironment.readLine();
		}
		return line;
	}
}

package hr.fer.zemris.java.hw06.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred koji predstavlja naredbu terminala koja ispisuje sadržaj datoteke na
 * izlaz terminala.
 * 
 * @author Branko Sirotković
 */
public class CatCommand implements ShellCommand {

	/**
	 * Kapacitet buffera s kojim čitamo datoteku.
	 */
	private static final int BUFFER_CAPACITY = 4096;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if (arguments == null) {
			env.writeln("This command requires arguments");
			return ShellStatus.CONTINUE;
		}

		String[] args = arguments.split(" ");

		if (args.length != 1 && args.length != 2) {
			env.writeln("Must provide unleast one argument.");
			return ShellStatus.CONTINUE;
		}

		Charset charset = Charset.defaultCharset();

		if (args.length == 2) {
			try {
				charset = Charset.forName(args[1]);
			} catch (IllegalArgumentException icne) {
				env.writeln("Unsupported charset " + args[1]);
				return ShellStatus.CONTINUE;
			}
		}

		try {
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])), charset));

			char[] cbuf = new char[BUFFER_CAPACITY];
			while (true) {
				int numOfChars = fileReader.read(cbuf);
				if (numOfChars < 1) {
					break;
				}
				String toWrite = new String(cbuf, 0, numOfChars);
				env.write(toWrite);
			}
			env.writeln("");
			fileReader.close();

		} catch (IOException ioe) {
			env.writeln("Error occured while reading file.");
			return ShellStatus.CONTINUE;
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Cat";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command cat takes one or two arguments. The first argument is path to some file and is mandatory.");
		list.add("The second argument is charset name that should be used to interpret chars from bytes.");
		list.add("If not provided, a default platform charset should be used.");
		list.add("This command opens given file and writes its content to console.");
		return list;
	}

}

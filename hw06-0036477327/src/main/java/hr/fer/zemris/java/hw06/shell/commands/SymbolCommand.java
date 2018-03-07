package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala. Naredba simbol može ispisati
 * trenutne specijalne simbole terminala ili ih postavljati na nove vrijednosti.
 * 
 * @author Branko Sirotković
 * @version 1.0
 *
 */
public class SymbolCommand implements ShellCommand {

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
			env.writeln("Must provide atleast one argument.");
			return ShellStatus.CONTINUE;
		}

		Character symbol;
		if (args[0].toLowerCase().equals("prompt")) {
			symbol = env.getPromptSymbol();
		} else if (args[0].toLowerCase().equals("morelines")) {
			symbol = env.getMorelinesSymbol();
		} else if (args[0].toLowerCase().equals("multiline")) {
			symbol = env.getMultilineSymbol();
		} else {
			env.writeln("Invalid command");
			return ShellStatus.CONTINUE;
		}
		if (args.length == 1) {
			env.writeln("Symbol for " + args[0] + " is '" + symbol + "'.");
		} else {
			char symbolAfter = args[1].charAt(0);

			if (args[0].toLowerCase().equals("prompt")) {
				env.setPromptSymbol(symbolAfter);
			} else if (args[0].toLowerCase().equals("morelines")) {
				env.setMorelinesSymbol(symbolAfter);
			} else if (args[0].toLowerCase().equals("multiline")) {
				env.setMultilineSymbol(symbolAfter);
			}

			env.writeln("Symbol for " + args[0] + " changed from '" + symbol + "' to '" + symbolAfter + "'.");
		}
		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Symbol";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		ArrayList<String> list = new ArrayList<>();
		list.add("If started with one argument: PROMPT, MORELINES or MULTILINE");
		list.add("it lists symbol for that function. If started with two arguments,");
		list.add("it sets symbol for that function to given value");
		return list;
	}

}

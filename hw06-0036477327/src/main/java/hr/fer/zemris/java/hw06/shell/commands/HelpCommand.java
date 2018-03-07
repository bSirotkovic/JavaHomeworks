package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala. Help naredba ispisuje sve
 * moguće naredbe terminala u slučaju da nema dodatnih argumenata, ili opis
 * naredbe primljene kao argument.
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class HelpCommand implements ShellCommand {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if (arguments == null) {
			for (Map.Entry<String, ShellCommand> entry : env.commands().entrySet()) {
				env.writeln(entry.getValue().getCommandName());
			}
		} else {
			ShellCommand command = env.getCommand(arguments);
			if (command == null) {
				env.writeln("That command does not exist");
				return ShellStatus.CONTINUE;
			}

			env.writeln(command.getCommandName());
			for (String string : command.getCommandDescription()) {
				env.writeln(string);
			}
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Help";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		ArrayList<String> list = new ArrayList<>();
		list.add("If started with no arguments, it lists names of all supported commands.");
		list.add("If started with single argument, it prints name and the description of selected command.");
		return list;
	}

}

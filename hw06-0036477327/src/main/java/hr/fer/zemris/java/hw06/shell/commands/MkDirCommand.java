package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala. Naredba stvara direktorij na
 * zadanoj putanji.
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class MkDirCommand implements ShellCommand {

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

		if (args.length != 1) {
			env.writeln("Must provide a path of the wanted directory.");
			return ShellStatus.CONTINUE;
		}

		try {
			Files.createDirectory(Paths.get(args[0]));
		} catch (IOException io) {
			env.writeln("Error while creating directory");
			return ShellStatus.CONTINUE;
		}

		env.writeln("Directory with name " + args[0] + " successfully created.");

		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "MkDir";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The mkdir command takes a single argument: directory name,");
		list.add("and creates the appropriate directory structure.");
		return list;
	}

}

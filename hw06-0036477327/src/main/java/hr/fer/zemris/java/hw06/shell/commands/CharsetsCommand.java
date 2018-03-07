package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim je implementirana naredba terminala koja ispisuje na izlaz
 * terminala sve Charsetove s kojima je u mogučnosti raditi.
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class CharsetsCommand implements ShellCommand {

	/**
	 * {@inheritDoc}
	 * @throws ShellIOExcpetion 
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		if (arguments != null) {
			env.writeln("charsets' command doesn't need any additional arguments");
			return ShellStatus.CONTINUE;
		}

		Map<String, Charset> map = Charset.availableCharsets();

		env.writeln("You can use one of this charsets: ");

		for (String charset : map.keySet()) {
			env.writeln(charset);
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Charsets";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command charsets takes no arguments and lists names of supported charsets for your Java platform.");
		return list;
	}

}

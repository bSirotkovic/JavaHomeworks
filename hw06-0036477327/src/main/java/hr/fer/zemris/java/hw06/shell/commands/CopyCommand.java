package hr.fer.zemris.java.hw06.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim je implementirana naredba terminala koja kopira datoteku sa
 * izvorne putanje zadane pri pozivu naredbe na odredišnu putanju, također
 * zadanu.
 * 
 * @author Branko Sirtković
 * @version 1.0
 */
public class CopyCommand implements ShellCommand {

	/**
	 * Kapacitet buffera s kojim se "prenose" podaci koji se kopiraju.
	 */
	private static final int BUFFER_CAPACITY = 4096;

	/**
	 * Putanja izvora datoteke.
	 */
	private Path src;
	/**
	 * Putanja odredišta datoteke.
	 */
	private Path dest;

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

		if (args.length != 2) {
			env.writeln("Two paths needed to copy. Source and destination path.");
			return ShellStatus.CONTINUE;
		}

		src = Paths.get(args[0]);
		dest = Paths.get(args[1]);

		if (!Files.exists(src)) {
			env.writeln("Provided source path doesn't exist.");
			return ShellStatus.CONTINUE;
		}

		// copying
		try {

			if (Files.exists(dest)) {
				env.writeln("Do you want the file to be overwritten?. Type \"y\" for yes");
				String result = env.readLine();

				if (!result.equals("y")) {
					return ShellStatus.CONTINUE;
				}
			}

			InputStream is = new BufferedInputStream(new FileInputStream(src.toFile()));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(dest.toFile()));

			byte[] buff = new byte[BUFFER_CAPACITY];
			while (true) {
				int numOfBytes = is.read(buff);
				if (numOfBytes < 1) {
					break;
				}
				os.write(buff, 0, numOfBytes);
				os.flush();
			}

			is.close();
			os.close();

			env.writeln("Copying done. Created " + dest.getFileName() + ".");

		} catch (IOException ioe) {
			env.writeln("Error while copying file " + src.getFileName());
			return ShellStatus.CONTINUE;
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Copy";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Copies src file given as first argument to dest file given as second argument");
		return list;
	}
}
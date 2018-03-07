package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala. Naredba Tree ispisuje sadržaj
 * direktorija i svih njegovih poddirektorija
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class TreeCommand implements ShellCommand {

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
			env.writeln("Must provide a path to the directory.");
			return ShellStatus.CONTINUE;
		}

		Path filePath = Paths.get(args[0]);
		if (!Files.exists(filePath)) {
			env.writeln("Provided path isn't a valid path.");
			return ShellStatus.CONTINUE;
		}

		try {
			Files.walkFileTree(filePath, new DirectoryTree(env));
		} catch (IOException ioe) {
			env.writeln("Error while going through directory");
			return ShellStatus.CONTINUE;
		}
		return ShellStatus.CONTINUE;
	}

	/**
	 * Statički ugnježđeni razred, služi kao konkretna implementacija
	 * FileVisitor-a.
	 * 
	 * @author Branko Sirotković
	 * @version 1.0
	 */
	private static class DirectoryTree implements FileVisitor<Path> {

		/**
		 * Udubljenja koja treba ispisivati ispred određene datoteke.
		 */
		private int indent = 0;

		/**
		 * Okruženje u kojem terminal djeluje.
		 */
		private Environment environment;

		public DirectoryTree(Environment en) {
			environment = en;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			printToOut(dir.getFileName().toString(), environment);
			indent += 2;
			return FileVisitResult.CONTINUE;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			printToOut(file.getFileName().toString(), environment);
			return FileVisitResult.CONTINUE;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			indent -= 2;
			return FileVisitResult.CONTINUE;
		}

		/**
		 * Metoda koja printa (u ispravnom formatu). Na izlaz terminala.
		 * 
		 * @param s
		 *            String kojega treba ispisati.
		 * @param env
		 *            Environment okruženje u kojem terminal djeluje.
		 */
		private void printToOut(String s, Environment env) {
			String spaces = (indent == 0 ? "" : String.format("%" + indent + "s", ""));
			env.write(spaces);
			env.writeln(s);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "Tree";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The tree command expects a single argument: directory name and prints a tree.");
		list.add("each directory level shifts output two charatcers to the right).");
		return list;

	}

}

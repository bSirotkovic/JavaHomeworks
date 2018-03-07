package hr.fer.zemris.java.hw06.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala koja ispisuje sadržaj zadanog
 * direktorija.
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class LsCommand implements ShellCommand {

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
			env.writeln("Must provide directory path.");
			return ShellStatus.CONTINUE;
		}

		File dir = Paths.get(args[0]).toFile();
		if (!dir.isDirectory()) {
			env.writeln("Provided file must be a directory.");
			return ShellStatus.CONTINUE;
		}

		try {
			for (File file : dir.listFiles()) {
				env.writeln(String.format("%s %10d %s %s", getAttributes(file.toPath()), getSize(file),
						getCreationTime(file.toPath()), file.getName()));
			}
		} catch (IOException ex) {
			env.writeln("Error occured while opening directory.");
			return ShellStatus.CONTINUE;
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda saznaje atribute datoteke/direktorija. drwx (directory, readable,
	 * writeable, executable).
	 * 
	 * @param filePath
	 *            Putanja datoteke/direktorija čiji atributi nas zanimaju.
	 * @return attributes Atributi datoteke/direktorija.
	 */
	private static String getAttributes(Path filePath) {
		char directory = '-';
		if (Files.isDirectory(filePath)) {
			directory = 'd';
		}

		char readable = '-';
		if (Files.isReadable(filePath)) {
			readable = 'r';
		}

		char writeable = '-';
		if (Files.isWritable(filePath)) {
			writeable = 'w';
		}

		char executable = '-';
		if (Files.isExecutable(filePath)) {
			executable = 'x';
		}

		return new StringBuilder().append(directory).append(readable).append(writeable).append(executable).toString();
	}

	/**
	 * Metoda koja vraća veličinu datoteke ili direktorija (sa svime u njemu).
	 * 
	 * @param file
	 *            datoteka/direktorij čija se veličina računa.
	 * @return Veličina datoteke/direktorija.
	 */
	private static long getSize(File file) {
		if (file.isFile()) {
			return file.length();
		}

		long size = 0;
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				size += f.length();
			} else {
				size += getSize(f);
			}
		}
		return size;
	}

	/**
	 * Vraća vrijeme kada je datoteka/direktorij nastao.
	 * 
	 * @param path
	 *            putanja do datoteke/direktorija čije vrijeme stvaranja nas
	 *            zanima.
	 * @return Datum i vrijeme stvaranja.
	 * @throws IOException
	 *             U slučaju neuspjelog čitanja datoteke.
	 */
	private static String getCreationTime(Path path) throws IOException {

		String formattedDateTime = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BasicFileAttributeView faView = Files.getFileAttributeView(path, BasicFileAttributeView.class,
				LinkOption.NOFOLLOW_LINKS);

		BasicFileAttributes attributes = faView.readAttributes();
		FileTime fileTime = attributes.creationTime();
		formattedDateTime = sdf.format(new Date(fileTime.toMillis()));

		return formattedDateTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "ls";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command ls takes a single argument – directory – and writes a directory listing.");
		return list;
	}

}

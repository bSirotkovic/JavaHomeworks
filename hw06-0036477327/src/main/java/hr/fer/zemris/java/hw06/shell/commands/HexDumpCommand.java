package hr.fer.zemris.java.hw06.shell.commands;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Razred kojim se implementira naredba terminala. HexDump naredba ispisuje
 * datoteku u posebnom heksadekadskom formatu zadanom u datoteci hw07.pdf
 * 
 * @author Branko Sirotković
 * @version 1.0
 */
public class HexDumpCommand implements ShellCommand {

	/**
	 * Konstanta koja opisuje koliko bajtova se ispisuje po retku.
	 */
	private static final int BYTES_IN_ROW = 16;

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
			env.writeln("Must proide a path to the file");
			return ShellStatus.CONTINUE;
		}

		Path src = Paths.get(args[0]);

		if (!Files.exists(src)) {
			env.writeln("Provided path is not valid");
			return ShellStatus.CONTINUE;
		}

		try {
			InputStream is = new BufferedInputStream(new FileInputStream(src.toFile()));

			byte[] buff = new byte[BYTES_IN_ROW];
			StringBuffer s = new StringBuffer();
			int numOfPrints = 0;

			while (true) {
				int numOfBytes = is.read(buff);
				if (numOfBytes < 1) {
					break;
				}
				s.append(String.format("%08x: ", numOfPrints));
				s.append(byteToHex(buff, numOfBytes));
				s.append(makeLetters(buff, numOfBytes));

				numOfPrints += BYTES_IN_ROW;

				env.writeln(s.toString());
				s.setLength(0);

			}

			is.close();

		} catch (IOException ioe) {
			env.writeln("Error with file/buffer.");
			return ShellStatus.CONTINUE;
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda kojom se bajtovi konvertiraju u slovo, ako to nije moguće onda u
	 * točku.
	 * 
	 * @param bytes
	 *            Bajtovi koji se konvertiraju.
	 * @param len
	 *            Efektivna veličina primljenog polja bajtova.
	 * @return String Slova i točaka dobivenih konverzijom.
	 */
	private static String makeLetters(byte[] bytes, int len) {

		StringBuffer buff = new StringBuffer();

		for (int i = 0; i < len; i++) {
			int value = bytes[i] & 0xff;
			if (value < 32 || value > 127) {
				buff.append('.');
			} else {
				buff.append((char) value);
			}
		}

		return buff.toString();
	}

	/**
	 * Metoda koja vraća String reprezentaciju polja bajtova.
	 * 
	 * @param bytes
	 *            Bajtovi koje želimo reprezentirati.
	 * @param len
	 *            Efektivna veličina polja bajtova.
	 * @return String vrijednost polja bajtova.
	 */
	private static String byteToHex(byte[] bytes, int len) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {

			if (i < len) {
				if ((bytes[i] & 0xff) < 0x10) {
					buff.append('0');
				}
				buff.append(Integer.toHexString(bytes[i] & 0xff).toUpperCase());
			} else {
				buff.append(" ");
			}

			if (i == bytes.length / 2 - 1) {
				buff.append("|");
			} else {
				buff.append(" ");
			}

		}
		return buff.append("| ").toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "HexDump";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The hexdump command expects a single argument: file name, and produces hex-output.");
		return list;
	}

}

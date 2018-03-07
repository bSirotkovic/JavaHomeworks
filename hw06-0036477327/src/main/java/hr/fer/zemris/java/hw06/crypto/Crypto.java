package hr.fer.zemris.java.hw06.crypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Razred koji pruža metode za AES enkripciju i dekripciju datoteke, te
 * izračunavanje SHA-256 vrijednosti datoteke. Programu se šalju argumenti
 * naredbenog retka, prvi argument može biti String "checksha" koji će potaknuti
 * računanje SHA-256 vrijednosti datoteke, a drugi argument mora biti ta
 * datoteka. Inače argument naredbenog retka mora biti String "encrypt" ili
 * "decrypt" a sljedeća dva argumenta su datoteka na koju se primjenjuje
 * algoritam te njen rezultat.
 * 
 * @author Branko
 *
 */
public class Crypto {

	/**
	 * Algoritam za računanje digesta.
	 */
	private static final String DIGEST_ALGORITHM = "SHA-256";

	/**
	 * Algoritam za računanje enkripcije i dekripcije.
	 */
	private static final String CRYPTING_ALGORITHM = "AES/CBC/PKCS5Padding";

	/**
	 * Veličina buffera prilikom čitanja i pisanja datoteka.
	 */
	private static final int BUFFER_SIZE = 1024;

	/**
	 * Metoda koja se pokreće pokretanjem programa.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args){
		
		try{	
			if(args.length == 2 || args[0].equals("checksha")){
				checkDigestResult(calculateDigest(getInputStream(args[1])), args[0]);
				System.exit(1);
			}
			
			else if(args.length == 3 && args[0].equals("encrypt")){
				cipherFile(getInputStream(args[1]), getOutputStream(args[2]), "encrypt");
			}
			
			else if(args.length == 3 && args[0].equals("decrypt")){
				cipherFile(getInputStream(args[1]), getOutputStream(args[2]), "decrypt");
			} 
			else{
				throw new IllegalArgumentException("Poslani su neispravni ulazni argumenti preko naredbeng retka.");		
			}
			
			printResultsOfCrypting(args[0], args[1], args[2]);
			
		}catch(Exception ex){
			System.out.println("Neuspjelo izvršavanje zadatka, evo još informacija o pogrešci: " + ex.getMessage());
		}
	}

	/**
	 * Pomoćna metoda koja ispisuje rezultat enkripcije odnosno dekripcije.
	 * @param method String koji označava jeli se radilo o enkripciji ili dekripciji.
	 * @param path1 Datoteka nad kojom se primjenjivao algoritam.
	 * @param path2 Datoteka koja je produkt algoritma.
	 */
	private static void printResultsOfCrypting(String method, String path1, String path2) {
		System.out.println(method +" completed. Generated file "+ path2 +" based on file "+ path1 +".");
	}

	/**
	 * Pomoćna metoda koja provjerava ispravnost digest algoritma.
	 * @param actualDigest Dobiveni digest nad datotekom.
	 * @param path Datoteka nad kojom se provodio algoritam.
	 */
	private static void checkDigestResult(String actualDigest, String path) {
		String expectedDigest = askUserForInfo("digest", 64);
		
		if(actualDigest.equals(expectedDigest)){
			System.out.println("Digesting completed. Digest of "+ path +" matches expected digest.");
		} else{			
			System.out.println("Digesting completed. Digest of "+ path +" does not match the expected digest. Digest was: " + actualDigest);
		}
	}

	/**
	 * Metoda koja provodi algoritam dekripcije odnosno enkripcije nad datotekom.
	 * @param is Ulazni tok podataka koji čita datoteku nad kojom provodimo algoritam.
	 * @param os Izlazni tok podataka koji zapisuje novo dobivenu datoteku u sustav.
	 * @param command String koji označava o kojem se algoritmu radi.
	 * @throws Exception Iznimke koje mogu nastati prilikom kriptiranja.
	 */
	private static void cipherFile(InputStream is, OutputStream os, String command) throws Exception {
		String keyText = askUserForInfo("key", 32);
		String ivText = askUserForInfo("vector", 32);

		SecretKeySpec keySpec = new SecretKeySpec(Util.hexToByte(keyText), "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hexToByte(ivText));
		Cipher cipher = Cipher.getInstance(CRYPTING_ALGORITHM);
		cipher.init(command.equals("encrypt") ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);

		byte[] inputBuffer = new byte[BUFFER_SIZE];
		byte[] outputBuffer = new byte[BUFFER_SIZE];

		while (true) {
			int numOfReadBytes = is.read(inputBuffer);
			if (numOfReadBytes < 1) {
				os.write(cipher.doFinal());
				break;
			}

			int numOfCyphredBytes = cipher.update(inputBuffer, 0, numOfReadBytes, outputBuffer);
			os.write(outputBuffer, 0, numOfCyphredBytes);
		}
		is.close();
		os.close();
	}

	/**
	 * Metoda koja zahtjeva od korisnika unos na standardni ulaz.
	 * @param whatInfo Što se od korisnika traži.
	 * @param expectedLength Očekivana duljina korisnikovog unosa
	 * @return String koji je korisnik unio.
	 */
	private static String askUserForInfo(String whatInfo, int expectedLength) {
		if (whatInfo.equals("key")) {
			System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):");
		} else if (whatInfo.equals("vector")) {
			System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
		} else if(whatInfo.equals("digest")){
			System.out.println("Please provide expected sha-256 digest:");
		} else {
			throw new IllegalArgumentException(
					"Metodi askUserForInfo je predan String koji nije ni \"key\" ni \"vector\"");
		}

		System.out.print(">");
		@SuppressWarnings("resource") // Scanner can't be closed because we're
										// reusing System.in. So we decide to
										// not close it as discussed in
										// lectures.
		Scanner sc = new Scanner(System.in);
		String info = sc.nextLine();

		if (info.length() != expectedLength) {
			throw new IllegalArgumentException("Predani ključ ili incijalizacijski vektor nisu 32-bitni.");
		}

		return info;
	}

	/**
	 * Metoda koja vraća izlazni tok podataka za određenu putanju.
	 * @param path Putanja datoteke.
	 * @return Izlazni tok podataka za tu datoteku.
	 */
	private static OutputStream getOutputStream(String path) {
		OutputStream os = null;

		try {
			os = new BufferedOutputStream(Files.newOutputStream(Paths.get(path)));
		} catch (IOException e) {
			System.out.println("Neuspjelo otvaranje izlazne datoteke na lokaciji: " + path);
			System.exit(1);
		}

		return os;
	}

	/**
	 * Metoda koja vraća ulazni tok podataka za određenu putanju.
	 * @param path Putanja datoteke.
	 * @return Ulazni tok podataka za tu datoteku.
	 */
	private static InputStream getInputStream(String path) {
		InputStream is = null;

		try {
			is = new BufferedInputStream(Files.newInputStream(Paths.get(path), StandardOpenOption.READ));
		} catch (IOException e) {
			System.out.println("Neuspjelo otvaranje ulazne datoteke na lokaciji: " + path);
			System.exit(1);
		}

		return is;
	}

	/**
	 * Metoda koja računa digest odnosno vrijednost datoteke nakon što se na nju primjeni SHA-256 algoritam.
	 * @param is Ulazni tok podataka.
	 * @return Digest datoteke.
	 * @throws NoSuchAlgorithmException Iznimka u slučaju da ne postoji taj algoritam. Nemoguće da se baci.
	 * @throws IOException Iznimka koja nastaje ukoliko nije moguće čitati sa ulaznog toka podataka.
	 */
	private static String calculateDigest(InputStream is) throws NoSuchAlgorithmException, IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);

		while (true) {
			int numOfReadBytes = is.read(buffer);
			if (numOfReadBytes < 1) break;

			md.update(buffer, 0, numOfReadBytes);
		}
		is.close();
		return Util.byteToHex(md.digest());
	}

}

package hr.fer.zemris.java.hw06.crypto;

/**
 * Pomoćni razred koji sadrži dvije statičke metode koje služe za prebacivanje znakovnog niza
 * koji predstavlja heksadekadski zapis znamenki u polje bitova i obrnuto.
 * @author Branko
 *
 */
public class Util {

	/**
	 * Statička metoda koja konvertira znakovni niz heksadekadskih znamenki u odgovarajuće polje bitova.
	 * @param hexFormat Znakovni niz heksadekadskih znamenki.
	 * @return Polje bitova.
	 */
	public static byte[] hexToByte(String hexFormat){
		if(hexFormat == null){
			throw new IllegalArgumentException("Pokušaj prebacivanja null String u byte[] format.");
		}
	
		if(hexFormat.length() == 0) return new byte[0];
		
		if(hexFormat.length() % 2 != 0){
			hexFormat = "0" + hexFormat;
		}
		
		byte[] byteFormat = new byte[hexFormat.length() / 2];
		
		for (int i = 0, n = byteFormat.length; i < n; i++){
			byteFormat[i] = convertCharToByte(hexFormat.charAt(2*i));
			byteFormat[i] = (byte) (byteFormat[i] << 4);
			byteFormat[i] += convertCharToByte(hexFormat.charAt(2*i + 1));
		}
		
		return byteFormat;
	}

	/**
	 * Pomoćna metoda koja jedan Character koji predstavlja heksadekadsku znamenku pretvara
	 * u njegovu binarnu reprezentaciju. Ukoliko Character nije heksadekadska znamenka baca se
	 * IllegalArgumentException.
	 * @param charAt Character koji se pretvara u bajt.
	 * @return byte, binarna reprezentacija heksadekske znamenke koja je primljena.
	 */
	private static byte convertCharToByte(char charAt) {
		if(Character.isDigit(charAt)) return (byte) (charAt - '0');
		if(charAt >= 'A' && charAt <= 'F') return (byte) (charAt - 'A' + 10);
		if(charAt >= 'a' && charAt <= 'f') return (byte) (charAt - 'a' + 10);
		
		throw new IllegalArgumentException("Metodi hexToByte je poslan String koji sadrži znamenke koje nisu heksadekadske.");
	}

	/**
	 * Metoda koja konvertira polje bitova u odgovarajući znakovni niz heksadekadskih znamenki.
	 * @param bytes Bitovi koje želimo konvertirati.
	 * @return Znakovni niz heksadekadskih znamenki.
	 */
	public static String byteToHex(byte[] bytes){
		StringBuilder hexFormat = new StringBuilder();
		
		for(int i = 0; i < bytes.length; i++){
			hexFormat.append(String.format("%02x", bytes[i]));
		}
		
		return hexFormat.toString();
	}
}

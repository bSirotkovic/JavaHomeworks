package hr.fer.zemris.java.hw01;
import java.util.Scanner;

/**
 * Razred koji služi za izračunavanje faktorijela prirodnog broja koji nije veći od 20.
 * Argumenti se šalju unosom na standardni ulaz sa tipkovnice. Argument mora biti prirodni broj manji od 21 
 * u protivnom je korisnik obaviješten o neispravnom unosu.
 * @author Branko Sirotković
 */
public class Factorial {

	/**
	 * Metoda koja se automatski pokreće pozivom programa.
	 * @param args Ne koriste se argumenti iz komandne linije.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Unesite broj > ");

			int n = 0;
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			} else {
				String input = sc.next();
				if (input.trim().equals("kraj")) {
					break;
				}
				System.out.printf("\'%s\' nije cijeli broj.%n", input);
				continue;
			}

			if (n < 1 || n > 20) {
				System.out.printf("\'%d\' nije u dozvoljenom rasponu.%n", n);
				continue;
			}

			System.out.printf("%d! = %d%n", n, factorial(n));
		}

		System.out.println("Doviđenja.");
		sc.close();
	}

	/**
	 * Metoda koja računa faktorijel prirodnog broj.
	 * @param n Broj čiji faktorijel se želi izračunati.
	 * @return Faktorijel ulaznog argumenta ili 0 ukoliko je broj van raspona [0, 20].
	 */
	public static long factorial(int n) {
		long res = 1;

		if (n < 1 || n > 20) {
			return 0;
		}

		while (n > 0) {
			res *= n;
			n--;
		}

		return res;
	}

}

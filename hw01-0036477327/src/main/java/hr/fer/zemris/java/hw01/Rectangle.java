package hr.fer.zemris.java.hw01;
import java.util.Scanner;

/**
 * Razred koji služi za unos duljina stranica pravokutnika te izračunavanje njegovog opsega i površine.
 * Argumenti se mogu predati preko naredbenog retka, u obliku 2 pozitivna racionalna broja. Ako se argumenti žele unijeti
 * tipkovnicom preko standardnog ulaza treba pokrenuti program sa 0 argumenata naredbenog retka. Ako u oba slučaja
 * broj nije racionalan i pozitivan korisnik je obaviješten o tome prikladno.
 * @author Branko Sirotković
 */
public class Rectangle {

	/**
	 * Metoda koja se automatski pokreće pozivom programa.
	 * @param args Argumenti se mogu slat iz naredbenog retka u paru. Moraju biti 2 pozitivna racionalna broja
	 * koja predstavljaju dimenzije pravokutnika.
	 */
	public static void main(String[] args) {
		double w = 0, h = 0;
		
		if(args.length == 2){
			try {
				w = Double.parseDouble(args[0]);
				h = Double.parseDouble(args[1]);
			} catch (NumberFormatException ex){
				System.out.println("Predani argumenti su neispravni.");
				System.exit(1);
			}
			if(!(w > 0 && h > 0)){
				System.out.println("Barem jedan od predanih argumenata je negativan.");
				System.exit(1);
			}
		} else if (args.length == 0){
			Scanner sc = new Scanner(System.in);
			w = inputValidDimension("širinu", sc);
			h = inputValidDimension("visinu", sc);
			sc.close();
		} else{
			System.out.println("Program je pokrenut s neispravnim brojem argumenata. Potrebna su 2 ili nijedan.");
			System.exit(1);
		}
		
		System.out.printf("Pravokutnik širine %.1f i visine %.1f ima površinu %.1f te opseg %.1f.%n",w, h, givePerimeter(w, h), giveArea(w, h));
		
	}
	
	/**
	 * Metoda koja računa površinu pravokutnika. Vraća 0 ukoliko je jedna od dimenzija neispravna.
	 * @param w Širina pravokutnika. Mora biti veća od 0.
	 * @param h Visina pravokutnika. Mora biti veća od 0.
	 * @return Površina pravokutnika.
	 */
	public static double giveArea(double w, double h) {
		if(w <= 0 || h <= 0){
			return 0;
		}
		
		return w*h;
	}

	/**
	 * Metoda koja računa opseg pravokutnika. Vraća 0 ukoliko je jedna od dimenzija neispravna.
	 * @param w Širina pravokutnika. Mora biti veća od 0.
	 * @param h Visina pravokutnika. Mora biti veća od 0.
	 * @return Opseg pravokutnika.
	 */
	public static double givePerimeter(double w, double h) {
		if(w <= 0 || h <= 0){
			return 0;
		}
		
		return 2*w + 2*h;
	}

	/**
	 * Pomoćna metoda koja sa standardnog ulaza učitava dimenziju pravokutnika, osigurava da je dimenzija ispravna
	 * (racionalni broj veći od 0) te komunicira sa korisnik dok ne unese ispravnu dimenziju.
	 * @param side Dimenzija koju želimo unijeti.
	 * @param sc Pomoćna varijabla Scanner koja čita sa standardnog ulaza.
	 * @return Uneseni double veći od 0.
	 */
	public static double inputValidDimension(String side, Scanner sc){
		double res;
		
		while(true){
			System.out.print("Unesite "+ side +" > ");

			if(!sc.hasNextDouble()){
				System.out.println(sc.next() + " se ne može protumačiti kao cijeli broj.");
				continue;
			}
			res = sc.nextDouble();

			if(res > 0) {
				break;
			}
			
			System.out.println("Unijeli ste negativnu vrijednost.");
		}
		
		
		return res;
	}

}

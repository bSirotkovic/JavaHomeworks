package hr.fer.zemris.java.hw01;
import java.util.Scanner;

/**
 * Razred koji služi za unos čvorova u binarno stabla te metode za manipulaciju tim stablom.
 * Uz razne metode za manipulaciju stablom ispisuje njegov rastući i padajući poredak.
 * @author Branko Sirotković
 */
public class UniqueNumbers {

	/**
	 * Unutarnji razred koji reprezentira jedan čvor stabla, čvor 
	 * sadrži reference na djecu te vrijednost u int obliku.
	 * @author Branko Sirotković
	 */
	public static class TreeNode {
		/*
		 * Lijevo dijete.
		 */
		TreeNode left;
		/*
		 * Desno dijete.
		 */
		TreeNode right;
		/*
		 * Vrijednost čvora.
		 */
		int value;
	}

	/**
	 * Metoda koja se automatski pokreće pozivom programa.
	 * @param args Ne koriste se argumenti naredbenog retka.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int value;
		String input = "";
		TreeNode root = null;
		
		while(true){
			System.out.print("Unesite broj > ");
			try{
				if(sc.hasNext()){
					input = sc.next();
					value = Integer.parseInt(input);
					root = addNode(root, value);
				}
			} catch (NumberFormatException ex){
				if(input.equals("kraj")){
					break;
				}
				System.out.println(input + " nije cijeli broj.");
				continue;
			}
		}
		
		System.out.print("Ispis od najmanjeg: ");
		printSorted(root);
		System.out.printf("%nIspis od najvećeg: ");
		printReverseSorted(root);
		//System.out.println("Broj elemenata u stablu: " + treeSize(root));
		
		sc.close();
	}

	/**
	 * Metoda koja ispisuje padajući redoslijed svih vrijednosti u binarnom stablu.
	 * @param glava Glava binarnog stabla.
	 */
	private static void printReverseSorted(TreeNode glava) {
		if(glava == null){
			return;
		}
		printReverseSorted(glava.right);
		System.out.print(glava.value + " ");
		printReverseSorted(glava.left);
	}

	/**
	 * Metoda koja ispisuje rastući redoslijed svih vrijednosti u binarnom stablu.
	 * @param glava Glava binarnog stabla.
	 */
	private static void printSorted(TreeNode glava) {
		if(glava == null){
			return;
		}
		printSorted(glava.left);
		System.out.print(glava.value + " ");
		printSorted(glava.right);
	}

	/**
	 * Metoda koja vraća boolean sadrži li binarno stablu vrijednost poslanu kao argument.
	 * @param glava Glava binardnog stabla.
	 * @param value Vrijednost koja se pretražuje u stablu.
	 * @return Boolean sadrži li stablo tu vrijednost.
	 */
	public static boolean containsValue(TreeNode glava, int value) {
		if (glava == null) {
			return false;
		}

		if (value < glava.value) {
			return containsValue(glava.left, value);
		} else if (value > glava.value) {
			return containsValue(glava.right, value);
		}
		return true;
	}

	/**
	 * Metoda koja vraća broj čvorova u stablu.
	 * @param glava Glava binarnog stabla.
	 * @return Broj čvorova u stablu.
	 */
	public static int treeSize(TreeNode glava) {
		if (glava == null) {
			return 0;
		}
		return treeSize(glava.left) + treeSize(glava.right) + 1;
	}

	/**
	 * Metoda za dodavanje int vrijednosti u stablo.
	 * @param glava Glava binarnog stabla.
	 * @param i Vrijednost koju želimo dodati u stablo.
	 * @return Glava binarnog stabla.
	 */
	public static TreeNode addNode(TreeNode glava, int i) {
		if (glava == null) {
			glava = new TreeNode();
			glava.value = i;
			System.out.println("Dodano.");
			return glava;
		}

		if (i == glava.value) {
			System.out.println("Broj već postoji. Preskačem.");
			return glava;
		}

		if (i < glava.value) {
			glava.left = addNode(glava.left, i);
		} else {
			glava.right = addNode(glava.right, i);
		}

		return glava;
	}

}
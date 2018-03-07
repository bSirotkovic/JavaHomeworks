package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.math.Complex;

public class Newton{

	public static void main(String[] args){
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
		System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
		
		int counter = 1;
		Scanner sc = new Scanner(System.in);
		List<Complex> list = new ArrayList<>();
		while(true){
			System.out.print("Root " + counter + "> ");
			String complexNum = sc.nextLine();
			if(complexNum.equals("done")) break;
			
			try{
				list.add(parseComplexNumber(complexNum));		
			} catch(NumberFormatException ex){
				System.out.println("To nije ispravan zapis kompleksnog broja. Pokušajte ponovno. Hint: " + ex.getMessage());
				continue;
			}
			
			counter++;
		}
		
		sc.close();

		if(list.size() < 2){
			System.out.println("Trebalo je unijeti barem dva korijena. Program se gasi");
			System.exit(1);
		}
		
		Complex[] array = new Complex[list.size()];
		list.toArray(array);
		FractalViewer.show(new FractalProducer(array));
	}

	private static Complex parseComplexNumber(String complexNum) {
		int index;
		boolean isNegative = false;
		if(complexNum.lastIndexOf('-') > 0){
			index = complexNum.lastIndexOf('-');
			isNegative = true;
		}
		else if(complexNum.lastIndexOf('+') > 0){
			index = complexNum.lastIndexOf('+');
		}
		else{
			if(complexNum.contains("i")){
				return new Complex(0, parseImg(complexNum, false));
			}
			return new Complex(Double.parseDouble(complexNum), 0);
		}
		
		return new Complex(Double.parseDouble(complexNum.substring(0, index)), parseImg(complexNum.substring(index + 1), isNegative));
	}
	
	private static double parseImg(String im, boolean isNegative){
		if(im.equals("i")) return 1;
		if(im.equals("-i")) return -1;
		if(im.endsWith("i")){
			
			double num = Double.parseDouble(im.substring(0, im.length() - 1));
			return isNegative ? -1*num : num;
		}
		
		throw new NumberFormatException("Imaginarni dio ne završava na i.");
	}
}

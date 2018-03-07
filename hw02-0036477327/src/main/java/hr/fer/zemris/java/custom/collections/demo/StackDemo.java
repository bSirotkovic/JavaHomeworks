package hr.fer.zemris.java.custom.collections.demo;

import java.util.EmptyStackException;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Demonstration class to check validity of ObjectStack class.
 * The program receives arguments through command prompt that should represent a
 * mathematical expression. Few examples:
 * Example 1: “8 2 /” means apply / on 8 and 2, so 8/2=4.
 * Example 2: “-1 8 2 / +” means apply / on 8 and 2, so 8/2=4, then apply + on -1 and 4, so the result is 3.
 * @author Branko Sirotković
 */
public class StackDemo {
	
	/**
	 * Method that is called when program starts running.
	 * @param args Arguments of the command prompt.
	 */
	public static void main(String[] args){
		if(args.length != 1){
			terminate();
		}
		
		ObjectStack stack = new ObjectStack();
		String[] splitString = args[0].trim().split(" +");
		
		
		for (int i = 0; i < splitString.length; i++) {
			splitString[i] = splitString[i].trim();
			
			if(isNumeric(splitString[i])){
				int number = Integer.parseInt(splitString[i]);
				stack.push(number);
			}
			
			else if(splitString[i].equals("+") || splitString[i].equals("-") || 
					splitString[i].equals("*") || splitString[i].equals("/") || 
					splitString[i].equals("%")){
				
				int firstNumber = 0, secondNumber = 0;
				try{
					secondNumber = (int)stack.pop();
					firstNumber = (int)stack.pop();					
				} catch (EmptyStackException ex){
					terminate();
				}
				int result = calculate(splitString[i], firstNumber, secondNumber);
				stack.push(result);
			}
			
			else{
				terminate();
			}
		}
		
		try{
			System.out.println("Expression evaluates to: "+stack.pop().toString());
		} catch (EmptyStackException ex){
			terminate();
		}
	}
	
	/**
	 * Method that tells the user that he gave inappropriate arguments,
	 * and that the program is shutting down.
	 */
	private static void terminate() {
		System.out.println("Uneseni su neispravni agrumenti. Program se gasi.");
		System.exit(1);
	}

	/**
	 * Method that calculates a mathematical operation between two given integers based on the string argument.
	 * @param string Can be '+','-','*','/' or '%', and method does an operation based on this string.
	 * @param firstNum First number in the operation.
	 * @param secondNum Second number in the operation.
	 * @return Returns the result of an mathematical operation.
	 */
	private static int calculate(String string, int firstNum, int secondNum) {
		if(secondNum == 0 && (string.equals("/") || string.equals("%"))){
			System.out.println("Pokušaj dijeljenja sa nulom. Program je prekinut.");
			System.exit(1);
		}
		
		if(string.equals("+")){
			return firstNum + secondNum;
		}
		else if(string.equals("-")){
			return firstNum - secondNum;
		}
		else if(string.equals("%")){
			return firstNum % secondNum;
		}
		else if(string.equals("*")){
			return firstNum * secondNum;
		} 
		else if(string.equals("/")){
			return firstNum / secondNum;
		} else {
			throw new IllegalArgumentException("Neispravan matematički operator je poslan u metodu.");
		}
	}

	/**
	 * Method that checks is the given string an Integer.
	 * @param string String to be checked.
	 * @return Returns true if string can be parsed to Integer, false otherwise.
	 */
	public static boolean isNumeric(String string){
		try{
			Integer.parseInt(string);
		} catch(NumberFormatException ex){
			return false;
		}
		return true;
	}
}

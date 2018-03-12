package demo;

import java.util.Arrays;

import hr.fer.zemris.bf.utils.Util;

public class ForEachDemo1 {

	public static void main(String[] args) {
		Util.forEach(
				Arrays.asList("A","B","C","Z"), 
				values -> 
					System.out.println(
						Arrays.toString(values)
							.replaceAll("true", "1")
							.replaceAll("false", "0")
					)
		);
	}
	
}

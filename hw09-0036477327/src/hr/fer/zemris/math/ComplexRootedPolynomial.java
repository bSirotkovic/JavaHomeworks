package hr.fer.zemris.math;

public class ComplexRootedPolynomial {

	private Complex[] roots;
	
	public ComplexRootedPolynomial(Complex ...roots) {
		if(roots == null || roots.length == 0){
			throw new IllegalArgumentException("Poslano je null ili prazno polje nultočaka.");
		}
		this.roots = roots;
	}
	
	public Complex apply(Complex z) {
		if(z == null){
			throw new IllegalArgumentException("Null kompleksni broj je predan kao argument.");
		}
		
		Complex toReturn = z.sub(roots[0]);
		for(int i = 1; i < roots.length; i++){
			toReturn = toReturn.multiply(z.sub(roots[i]));
		}
		
		return toReturn;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("f(z) = ");
		for(int i = 0; i < roots.length; i++){
			sb.append("(z-" + roots[i] + ")");
			
			if(i != roots.length - 1){
				sb.append("*");
			}
		}
		
		return sb.toString();
	}
	
	public ComplexPolynomial toComplexPolynom() {
		ComplexPolynomial toReturn = new ComplexPolynomial(roots[0].negate(), Complex.ONE);
		
		for(int i = 1; i < roots.length; i++){
			ComplexPolynomial poly = new ComplexPolynomial(roots[i].negate(), Complex.ONE);
			
			toReturn = toReturn.multiply(poly);
		}
		
		return toReturn;
	}
	
	public int indexOfClosestRootFor(Complex z, double treshold) {
		if(z == null){
			throw new IllegalArgumentException("Null kompleksni broj je predan kao argument.");
		}
		if(treshold < 0){
			throw new IllegalArgumentException("Treshold mora biti nenegativni broj.");
		}
		
		double value = treshold;
		int index = -1;
		
		for(int i = 0; i < roots.length; i++){
			if(z.sub(roots[i]).module() < value){
				value = z.sub(roots[i]).module();
				index = i;
			}
		}
		
		return index;
	}
}

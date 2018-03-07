package hr.fer.zemris.math;

public class ComplexPolynomial {

	private Complex[] factors;

	public ComplexPolynomial(Complex... factors) {
		if (factors == null || factors.length == 0) {
			throw new IllegalArgumentException("Null ili prazno polje koeficijenata je predano kao argument.");
		}
		this.factors = factors;
	}

	public short order() {
		return (short) (factors.length - 1);
	}

	public ComplexPolynomial multiply(ComplexPolynomial p) {
		if(p == null){
			throw new IllegalArgumentException("Null polinom je predan kao argument");
		}

		Complex[] newFactors = new Complex[order() + p.order() + 1];
		
		for(int i = 0; i < factors.length; i++){
			for(int j = 0; j < p.factors.length; j++){
				if(newFactors[i+j] == null){
					newFactors[i+j] = factors[i].multiply(p.factors[j]);
				}
				else{
					newFactors[i+j] = newFactors[i+j].add(factors[i].multiply(p.factors[j]));
				}
			}
		}
		
		return new ComplexPolynomial(newFactors);
	}

	public ComplexPolynomial derive() {
		Complex[] newFactors = new Complex[order()];
		
		for(int i = 1; i < factors.length; i++){
			newFactors[i-1] = factors[i].multiply(new Complex(i, 0));
		}
		
		return new ComplexPolynomial(newFactors);
	}

	public Complex apply(Complex z) {
		if (z == null) {
			throw new IllegalArgumentException("Null kompleksni broj je predan kao argument.");
		}

		Complex toReturn = factors[0];

		for (int i = 1; i < factors.length; i++) {
			toReturn = toReturn.add(z.power(i).multiply(factors[i]));
		}

		return toReturn;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("f(z) = ");
		for(int i = 0; i < factors.length; i++){
			sb.append(factors[i] + "*z^" + i);
			
			if(i != factors.length - 1){
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
}

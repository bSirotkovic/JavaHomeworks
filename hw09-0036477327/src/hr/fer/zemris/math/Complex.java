package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

public class Complex {

	private double re;
	private double im;
	
	public static final Complex ZERO = new Complex(0,0);
	public static final Complex ONE = new Complex(1,0);
	public static final Complex ONE_NEG = new Complex(-1,0);
	public static final Complex IM = new Complex(0,1);
	public static final Complex IM_NEG = new Complex(0,-1);

	public Complex() {
		this(0, 0);
	}

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public double module() {
		return Math.sqrt(re * re + im * im);
	}
	
	public Complex multiply(Complex c) {
		if(c == null){
			throw new IllegalArgumentException("Poslan je null Complex kao argument.");
		}
		
		return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
	}
	
	public Complex divide(Complex c) {
		if(c == null){
			throw new IllegalArgumentException("Poslan je null Complex kao argument.");
		}
		
		Complex multiplicator = new Complex(c.re, -1 * c.im);
		Complex numerator = this.multiply(multiplicator);
		Complex denominator = c.multiply(multiplicator);
		
		if(denominator.re == 0){
			throw new IllegalArgumentException("Ovaj kompleksni broj se ne može podijeliti s brojem: " + c);
		}
		
		return new Complex(numerator.re / denominator.re, numerator.im / denominator.re);
	}
	
	public Complex add(Complex c) {
		if(c == null){
			throw new IllegalArgumentException("Poslan je null Complex kao argument.");
		}
		
		return new Complex(re + c.re, im + c.im);
	}
	
	public Complex sub(Complex c) {
		if(c == null){
			throw new IllegalArgumentException("Poslan je null Complex kao argument.");
		}
		
		return new Complex(re - c.re, im - c.im);
	}
	
	public Complex negate() {
		return new Complex(-1 * re, -1 * im);
	}
	
	public Complex power(int n) {
		if(n < 0){
			throw new IllegalArgumentException("Potencija mora biti nenegativni broj.");
		}
		
		double r = module();
		double angle = Math.atan2(im, re);
		
		return new Complex(Math.pow(r, n) * Math.cos(n * angle), Math.pow(r, n) * Math.sin(n * angle));
	}
	
	public List<Complex> root(int n) {
		if(n <= 0){
			throw new IllegalArgumentException("Red korijena mora biti pozitivni broj.");
		}
		double r = module();
		double angle = Math.atan2(im, re);
		
		ArrayList<Complex> list = new ArrayList<>();
		
		for(int k = 0; k < n; k++){
			list.add(new Complex(Math.pow(r, 1./n) * Math.cos((angle + 2*k*Math.PI)/n), 
					Math.pow(r, 1./n) * Math.sin((angle + 2*k*Math.PI)/n)));
		}
		
		return list;
	}
	
	@Override
	public String toString() {
		return String.format("[%f, %fi]", re, im);
	}
	
	public boolean equals(Object other){
		if(!(other instanceof Complex)) return false;
		Complex c = (Complex) other;
		return Math.abs(re - c.re) < 1e-5 && Math.abs(im - c.im) < 1e-5;
	}
}

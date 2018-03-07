package hr.fer.zemris.java.hw02;

/**
 * Class that represents a complex number and allows basic manipulations with it.
 * @author Branko Sirotković
 */
public class ComplexNumber {

	/**
	 * real component of a complex number.
	 */
	private final double realPart;
	/**
	 * imaginary component of a complex number.
	 */
	private final double imgnPart;
	
	/**
	 * Constructor that initializes variables realPart and imgnPart
	 * @param realPart real component of a complex number
	 * @param imgnPart imaginary component of a complex number
	 */
	public ComplexNumber(double realPart, double imgnPart){
		this.realPart = realPart;
		this.imgnPart = imgnPart;
	}
	
	/**
	 * Method that makes a new instance of a ComplexNumber that has real part
	 * equal to the given parameter and imaginary part = 0.
	 * @param realPart real component of a complex number we wish to instance
	 * @return A new instance of ComplexNumber
	 */
	public static ComplexNumber fromReal(double realPart){
		return new ComplexNumber(realPart, 0.0);
	}
	
	/**
	 * Method that makes a new instance of a ComplexNumber that has imaginary part
	 * equal to the given parameter and real part = 0.
	 * @param imgnPart imaginary component of a complex number we wish to instance
	 * @return A new instance of ComplexNumber
	 */
	public static ComplexNumber fromImaginary(double imgnPart){
		return new ComplexNumber(0.0, imgnPart);
	}
	
	/**
	 * Method that makes a new instance of a ComplexNumber from its parameters in polar coordinates
	 * @param magnitude r parameter in polar coordinates.
	 * @param angle Angle of a polar coordinate.
	 * @return Returns a new instance of a complex number based on given arguments.
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle){
		return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
	}
	
	/**
	 * Method that parses strings and returns a new instance of ComplexNumber if string can be parsed.
	 * @param s String to be parsed.
	 * @return Returns a new instance of a complex number based on given arguments.
	 */
	public static ComplexNumber parse(String s){
		double realPart = 0;
		double imgnPart = 0;
		
		try{
			s = s.trim();
			int index = s.lastIndexOf('+');
			if(index > 0){ //slučaj kada a+bi;
				realPart = Double.parseDouble(s.substring(0, index));
				imgnPart = parseImgn(s.substring(index + 1));
				return new ComplexNumber(realPart, imgnPart);
			}
		
			index = s.lastIndexOf('-');
			if(index > 0){ //slučaj a-bi
				realPart = Double.parseDouble(s.substring(0, index));
				imgnPart = parseImgn(s.substring(index));
				return new ComplexNumber(realPart, imgnPart);
			}
		
			if(s.endsWith("i")){ //bi
				imgnPart = parseImgn(s);
				return fromImaginary(imgnPart);
			}
		
			realPart = Double.parseDouble(s); //a
			return fromReal(realPart);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Predani string ne odgovara regularnom izrazu za kompleksni broj.");
		}
		
	}
	
	/**
	 * Helper method of parse method. Parses the remaining part of the string that should represent the imaginary component
	 * @param string String to be parsed.
	 * @return Double that represents the value of an imaginary component
	 */
	private static double parseImgn(String string) {
		if(string.equals("i")){
			return 1.0;
		}
		if(string.equals("-i")){
			return -1.0;
		}
		else{
			return Double.parseDouble(string.substring(0,string.length()-1));
		}
	}

	/**
	 * getter for realPart
	 * @return realPart
	 */
	public double getReal(){
		return realPart;
	}
	
	/**
	 * getter for imgnPart
	 * @return imgnPart
	 */
	public double getImaginary(){
		return imgnPart;
	}
	
	/**
	 * Method that calculates magnitude of a complex number
	 * @return magnitude of this complex number
	 */
	public double getMagnitude(){
		return Math.sqrt(realPart*realPart + imgnPart*imgnPart);
	}
	
	/**
	 * Method that calculates an angle of a complex number
	 * @return angle of this complex number
	 */
	public double getAngle(){
		double angle = Math.atan2(imgnPart, realPart);
		if(angle < 0){
			angle = 2*Math.PI + angle;
		}
		return angle;
	}
	
	/**
	 * Method that adds two complex numbers.
	 * @param c complex number to add to this one.
	 * @return Returns a new instance of a complex number that represents the sum.
	 */
	public ComplexNumber add(ComplexNumber c){
		if(c == null){
			return this;
		}
		return new ComplexNumber(realPart + c.getReal(), imgnPart + c.getImaginary());
	}
	
	/**
	 * Method that calculates difference between two complex numbers.
	 * @param c complex number to sub from this one.
	 * @return Returns a new instance of a complex number that represents the difference.
	 */
	public ComplexNumber sub(ComplexNumber c){
		if(c == null){
			return this;
		}
		return new ComplexNumber(realPart - c.getReal(), imgnPart - c.getImaginary());
	}
	
	/**
	 * Method that multiplies two complex numbers.
	 * @param c complex number to multiply to this one.
	 * @return Returns a new instance of a complex number that represents the multiplication.
	 */
	public ComplexNumber mul(ComplexNumber c){
		if(c == null){
			return this;
		}
		double newRealPart = realPart * c.getReal() + -1 * imgnPart * c.getImaginary();
		double newImgnPart = realPart * c.getImaginary() + imgnPart * c.getReal();
		
		return new ComplexNumber(newRealPart, newImgnPart);
	}
	
	/**
	 * Method that divides two complex numbers.
	 * @param c complex number to divide from this one.
	 * @return Returns a new instance of a complex number that represents the divison.
	 */
	public ComplexNumber div(ComplexNumber c){
		if(c == null){
			return this;
		}
		if(c.equals(0)){
			throw new IllegalArgumentException("Pokušaj dijeljenja sa nulom");
		}
		ComplexNumber conjugate = new ComplexNumber(c.getReal(), -1 * c.getImaginary());
		ComplexNumber mulNumerator = this.mul(conjugate);
		ComplexNumber mulDenominator = c.mul(conjugate);
		
		return new ComplexNumber(mulNumerator.getReal() / mulDenominator.getReal(), mulNumerator.getImaginary() / mulDenominator.getReal());
	}	
	
	/**
	 * Method that calculates power of a compley number.
	 * @param n Power degree.
	 * @return Returns a new instance of a complex number.
	 * @throws IllegalArgumentException if n < 0
	 */
	public ComplexNumber power(int n) throws IllegalArgumentException{
		if(n < 0){
			throw new IllegalArgumentException("Potencija ne smije biti manja od 0.");
		}
		
		return fromMagnitudeAndAngle(Math.pow(this.getMagnitude(), n), n * this.getAngle() % (2*Math.PI));
	}
	
	/**
	 * Method that calculates all roots of a complex number.
	 * @param n How many roots and of what degree we want.
	 * @return Returns an array of all roots of a complex number.
	 * @throws IllegalArgumentException if n < 1
	 */
	public ComplexNumber[] root(int n) throws IllegalArgumentException{
		if(n < 1){
			throw new IllegalArgumentException("Red korijena mora biti minimalno 1.");
		}
		
		ComplexNumber[] allSolutions = new ComplexNumber[n];
		
		double magnitude = Math.pow(getMagnitude(), 1.0/n);
		double angle = this.getAngle() / n;
		
		for(int i = 0; i < n; i++){
			allSolutions[i] = fromMagnitudeAndAngle(magnitude, angle);
			angle += (2 * Math.PI) / n;
		}
		return allSolutions;
	}
	
	/**
	 * Method that converts a complex number to a string.
	 * @return String representation of ComplexNumber.
	 */
	public String toString(){
		char sign;
		if(imgnPart > 0){
			sign = '+';
		}
		else {
			sign = '-';
		}
		return String.format("%.3f %c %.3fi", realPart, sign, Math.abs(imgnPart));
		
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ComplexNumber)){
			return false;
		}
		ComplexNumber c = (ComplexNumber) obj;
		if(Math.abs(c.realPart - realPart) < 1e-3  && Math.abs(c.imgnPart - imgnPart) < 1e-3){
			return true;
		}
		
		return false;
	}
}

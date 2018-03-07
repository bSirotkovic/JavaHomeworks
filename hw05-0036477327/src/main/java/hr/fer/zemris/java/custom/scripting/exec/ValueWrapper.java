package hr.fer.zemris.java.custom.scripting.exec;

/**
 * Razred koji predstavlja wrapper bilo kojeg objekta odnosno njegove vrijednosti. Te omogućava osnovne
 * aritmetičke operacije nad tom vrijednošću, ako je Object tipa String, Double, Integer ili je jedank null.
 * @author Branko Sirotković
 *
 */
public class ValueWrapper {

	/**
	 * Vrijednost wrappera.
	 */
	private Object value;
	
	/**
	 * Konstruktor koji inicijalizira vrijednost wrappera.
	 * @param value Vrijednost wrappera.
	 */
	public ValueWrapper(Object value){
		this.value = value;
	}
	
	/**
	 * Getter za Value.
	 * @return value.
	 */
	public Object getValue(){
		return value;
	}
	
	/**
	 * Setter za value.
	 * @param value Vrijednost wrappera.
	 */
	public void setValue(Object value){
		this.value = value;
	}

	/**
	 * Metoda koja dodaje primljeni objekt na trenutnu vrijednost.
	 * @param incValue Pribrojnik.
	 */
	public void add(Object incValue){
		Number arg1 = prepareArgument(value);
		Number arg2 = prepareArgument(incValue);
		
		if(isOneOfThemDouble(arg1, arg2)){
			value = arg1.doubleValue() + arg2.doubleValue();
		} else {
			value = arg1.intValue() + arg2.intValue();			
		}
		
	}
	
	/**
	 * Metoda koja oduzima primljeni objekt od trenutne vrijednosti.
	 * @param decValue Umanjitelj.
	 */
	public void subtract(Object decValue){
		Number arg1 = prepareArgument(value);
		Number arg2 = prepareArgument(decValue);
		
		if(isOneOfThemDouble(arg1, arg2)){
			value = arg1.doubleValue() - arg2.doubleValue();
		} else {
			value = arg1.intValue() - arg2.intValue();			
		}
		
	}
	
	/**
	 * Metoda koja množi primljeni objekt sa trenutnom vrijednošću.
	 * @param mulValue Faktor.
	 */
	public void multiply(Object mulValue){
		Number arg1 = prepareArgument(value);
		Number arg2 = prepareArgument(mulValue);
		
		if(isOneOfThemDouble(arg1, arg2)){
			value = arg1.doubleValue() * arg2.doubleValue();
		} else {
			value = arg1.intValue() * arg2.intValue();			
		}
		
	}
	
	/**
	 * Metoda koja trenutnu vrijednost dijeli sa primljenim objektom.
	 * @param divValue Dijeljitelj.
	 */
	public void divide(Object divValue){
		Number arg1 = prepareArgument(value);
		Number arg2 = prepareArgument(divValue);
		
		if(isOneOfThemDouble(arg1, arg2)){
			value = arg1.doubleValue() / arg2.doubleValue();
		} else {
			value = arg1.intValue() / arg2.intValue();			
		}
		
	}
	
	/**
	 * Metoda koja uspoređuje trenutnu vrijednost sa primljenim objektom.
	 * @param withValue Objekt s kojim se uspoređuje.
	 * @return int koji je -1 ukoliko je trenutna vrijednost manja, 1 ako je veća, a 0 ako su jednake.
	 */
	public int numCompare(Object withValue){
		Number arg1 = prepareArgument(value);
		Number arg2 = prepareArgument(withValue);
		
		return Double.compare(arg1.doubleValue(), arg2.doubleValue());
	}
	
	/**
	 * Metoda koja provjerava je li objekt adekvatan za aritmetičke operacije.
	 * @param arg Objekt koji se provjerava.
	 * @return boolean je li adekvatan.
	 */
	private boolean checkifValidObject(Object arg) {
		return arg == null || arg instanceof String || arg instanceof Integer || arg instanceof Double;
	}
	
	/**
	 * Metoda koja priprema argument za aritemtičku operaciju.
	 * @param otherValue Argument koji se priprema.
	 * @return Number, argument u valjanom obliku za aritemtičku operaciju.
	 */
	private Number prepareArgument(Object otherValue){
		if(!checkifValidObject(otherValue)){
			throw new IllegalArgumentException("Operand aritmetičke operacije nad ValueWrapperom mora biti String, Double, Integer ili null.");
		}
		
		if(otherValue == null){
			return Integer.valueOf(0);
		}
		
		if(otherValue instanceof String){
			if(((String) otherValue).contains(".") || ((String) otherValue).toLowerCase().trim().contains("e")){
				otherValue = Double.parseDouble((String) otherValue);
			} else {
				otherValue = Integer.parseInt((String) otherValue);
			}
		}
		
		return (Number) otherValue;
	}
	
	/**
	 * Metoda koja provjerava je li jedan od dva primljena argumenta tipa Double.
	 * @param arg1 Prvi argument.
	 * @param arg2 Drugi argument.
	 * @return boolean je li jedan od njih Double.
	 */
	private boolean isOneOfThemDouble(Number arg1, Number arg2){
		return arg1 instanceof Double || arg2 instanceof Double;
	}
}

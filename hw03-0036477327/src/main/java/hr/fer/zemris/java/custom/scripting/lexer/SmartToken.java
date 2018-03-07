package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Token čije instance generira SmartScriptLexer.
 * @author Branko Sirotković
 *
 */
public class SmartToken {

	/**
	 * Vrijednost tokena.
	 */
	private Object value;
	
	/**
	 * Tip tokena.
	 */
	private SmartTokenType type;
	
	/**
	 * Konstruktor tokena. Tip ne smije biti null.
	 * @param type Tip tokena.
	 * @param value Vrijednost tokena.
	 */
	public SmartToken(SmartTokenType type, Object value){
		if(type == null){
			throw new IllegalArgumentException("U konstruktor pametnog tokena je poslan SmartTokenType null.");
		}
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Getter za varijablu type.
	 * @return type.
	 */
	public SmartTokenType getType(){
		return type;
	}
	
	/**
	 * Getter za varijablu value.
	 * @return value.
	 */
	public Object getValue(){
		return value;
	}
}

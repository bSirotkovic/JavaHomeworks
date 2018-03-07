package hr.fer.zemris.java.custom.collections;

/**
 * Exception class, occurs when we try to call a method involving a stack but is unable to
 * complete because it is empty.
 * @author Branko Sirotković
 * @author 1.0
 *
 */
public class EmptyStackException extends RuntimeException{
	
	/**
	 * Serial ID of EmptyStackException class.
	 */
	private static final long serialVersionUID = 5019575938076748881L;

	/**
	 * Default constructor.
	 */
	public EmptyStackException(){
		super();
	}

	/**
	 * Constructor that receives a message about the error.
	 * @param string Helpful message about an error that occured.
	 */
	public EmptyStackException(String string) {
		super(string);
	}
}

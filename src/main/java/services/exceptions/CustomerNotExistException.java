package services.exceptions;

/**
 * Excepcion que se lanza cuando se inserta una orden con un cliente que no
 * existe
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class CustomerNotExistException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	protected Throwable throwable;

	/**
	 * Method 'CustomerNotExistException'
	 */
	public CustomerNotExistException() {
		super();
	}

	/**
	 * Method 'CustomerNotExistException'
	 * 
	 * @param message
	 */
	public CustomerNotExistException(String message) {
		super(message);
	}

	/**
	 * Method 'CustomerNotExistException'
	 * 
	 * @param cause
	 */
	public CustomerNotExistException(Throwable cause) {
		super(cause);
		this.throwable = cause;
	}

	/**
	 * Method 'CustomerNotExistException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CustomerNotExistException(String message, Throwable cause) {
		super(message, cause);
		this.throwable = cause;
	}

	/**
	 * Method 'CustomerNotExistException'
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomerNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.throwable = cause;
	}

	/**
	 * Retorna la causa de la Excepcion
	 */
	public Throwable getCause() {
		return this.throwable;
	}

}

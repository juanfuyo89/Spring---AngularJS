package services.exceptions;

/**
 * Excepcion que se lanza cuando se inserta una orden con un producto no
 * permitido para un cliente
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class ProductNotAllowedException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	protected Throwable throwable;

	/**
	 * Method 'ProductNotAllowedException'
	 */
	public ProductNotAllowedException() {
		super();
	}

	/**
	 * Method 'ProductNotAllowedException'
	 * 
	 * @param message
	 */
	public ProductNotAllowedException(String message) {
		super(message);
	}

	/**
	 * Method 'ProductNotAllowedException'
	 * 
	 * @param cause
	 */
	public ProductNotAllowedException(Throwable cause) {
		super(cause);
		this.throwable = cause;
	}

	/**
	 * Method 'ProductNotAllowedException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ProductNotAllowedException(String message, Throwable cause) {
		super(message, cause);
		this.throwable = cause;
	}

	/**
	 * Method 'ProductNotAllowedException'
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProductNotAllowedException(String message, Throwable cause, boolean enableSuppression,
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

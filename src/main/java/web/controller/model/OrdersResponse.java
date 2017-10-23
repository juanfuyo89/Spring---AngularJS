package web.controller.model;

public class OrdersResponse {
	
	/**
     * Utilizamos el patron singleton
     */
	private static OrdersResponse ordersResponse;
	private String responseCode;
	private String message;
	
	/**
     * Constructor privado, para implementar el patron singleton
     */
	private OrdersResponse(String responseCode, String message) {
		this.responseCode = responseCode;
		this.message = message;
	}
	
	/**
     * Metodo que Crea una nueva y unica instancia de OrdersResponse si es
     * que no existe
     * 
     * @return the controlPersona (Controlador de Personas)
     */
	public static OrdersResponse getInstance(String responseCode, String message) {
		if (ordersResponse==null) {
			return new OrdersResponse(responseCode, message);
		}
		ordersResponse.setResponseCode(responseCode);
		ordersResponse.setMessage(message);
		return ordersResponse;
	}
	
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdersResponse [responseCode=" + responseCode + ", message=" + message + "]";
	}
	
}

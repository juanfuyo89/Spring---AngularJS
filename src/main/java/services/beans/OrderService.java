package services.beans;

import java.util.List;
import data.model.Order;
import services.exceptions.CustomerNotExistException;
import services.exceptions.ProductNotAllowedException;
import web.controller.model.OrdersQuery;

/**
 * Interface para definir el Bean de servicios
 * 
 * @author Juan Carlas Fuyo
 */
public interface OrderService {

	/**
	 * Crea una nueva orden en Sistema
	 * 
	 * @param order
	 * @throws CustomerNotExistException
	 * @throws ProductNotAllowedException
	 */
	public void createOrder(Order order) throws CustomerNotExistException, ProductNotAllowedException;

	/**
	 * Retorna una lista de ordenes con base en el cliente, la fecha inicial y la
	 * fecha final
	 * 
	 * @param orderQuery
	 * @return List<Order>
	 * @throws CustomerNotExistException 
	 */
	public List<Order> getOrdersByCustomer(OrdersQuery orderQuery) throws CustomerNotExistException;

}

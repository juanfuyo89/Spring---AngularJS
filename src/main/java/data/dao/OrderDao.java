package data.dao;

import java.util.List;
import data.model.Order;
import web.controller.model.OrdersQuery;

/**
 * Interface para definir el DAO de tipo Order
 * 
 * @author Juan Carlas Fuyo
 */
public interface OrderDao {

    /**
     * Retorna todas las ordenes en sistema.
     * 
     * @return List<Order>
     */
    public List<Order> findAllOrders();

    /**
     * Retorna una orden con base en su Id
     * 
     * @param order
     * @return Order
    public Order findOrderById(Order order);
    */

    /**
     * Retorna una lista de ordenes con base en el cliente
     * 
     * @param orderQuery
     * @return List<Order>
     */
    public List<Order> findOrdersByCustomer(OrdersQuery orderQuery);

    /**
     * Inserta una nueva orden en Sistema
     * 
     * @param order
     */
    public void insertOrder(Order order);

}

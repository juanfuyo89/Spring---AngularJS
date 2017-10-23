package services.beans.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.dao.CustomerDao;
import data.dao.OrderDao;
import data.dao.ProductDao;
import data.dao.ProductsAllowedDao;
import data.model.Customer;
import data.model.Order;
import data.model.OrderDetail;
import data.model.Product;
import data.model.ProductsAllowed;
import services.beans.OrderService;
import services.exceptions.CustomerNotExistException;
import services.exceptions.ProductNotAllowedException;
import web.controller.model.OrdersQuery;

/**
 * Clase que implementa un Bean de servicios para el manejo de ordenes de
 * compra.
 * 
 * @author Juan Carlas Fuyo
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	/**
	 * Inyeccion del DAO de tipo Customer
	 */
	@Autowired
	private CustomerDao customerDao;

	/**
	 * Inyeccion del DAO de tipo Product
	 */
	@Autowired
	private ProductDao productDao;

	/**
	 * Inyeccion del DAO de tipo Order
	 */
	@Autowired
	private OrderDao orderDao;

	/**
	 * Inyeccion del DAO de tipo ProductsAllowed
	 */
	@Autowired
	private ProductsAllowedDao productsAllowedDao;

	public void createOrder(Order order) throws CustomerNotExistException, ProductNotAllowedException {
		if (!this.validateAllowProducts(order)) {
			throw new ProductNotAllowedException("Uno o mas productos no estan disponibles para el Cliente");
		}
		order.setCreationDate(new Date());
		List<OrderDetail> orderDetails = order.getOrderDetails();
		for (OrderDetail orderDetail : orderDetails) {
			orderDetail.setOrder(order);
		}
		orderDao.insertOrder(order);
	}

	public List<Order> getOrdersByCustomer(OrdersQuery orderQuery) throws CustomerNotExistException {
		orderQuery.setCustomer(this.validateCustomer(orderQuery.getCustomer()));
		List<Order> orders = orderDao.findOrdersByCustomer(orderQuery);
		return orders;
	}

	/**
	 * Valida si los productos en la orden están disponibles para el Cliente
	 * 
	 * @param order
	 * @return
	 * @throws CustomerNotExistException
	 */
	private boolean validateAllowProducts(Order order) throws CustomerNotExistException {
		Customer customer = this.validateCustomer(order.getCustomer());
		order.setCustomer(customer);
		List<ProductsAllowed> productsAllowed = productsAllowedDao.findProductsAllowedByCustomer(customer);
		List<Product> products = new ArrayList<Product>();
		for (ProductsAllowed productAllowed : productsAllowed) {
			products.add(productAllowed.getProduct());
		}
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			Product product = productDao.findProductById(orderDetail.getProduct());
			if (product == null) {
				return false;
			}
			if (!products.contains(product)) {
				return false;
			}
			orderDetail.setProduct(product);
		}
		return true;
	}

	/**
	 * Valida la existencia del cliente en sistema.
	 * 
	 * @param customer
	 * @return
	 * @throws CustomerNotExistException
	 */
	private Customer validateCustomer(Customer customer) throws CustomerNotExistException {
		customer = customerDao.findCustomerById(customer);
		if (customer == null) {
			throw new CustomerNotExistException("El cliente no existe");
		}
		return customer;
	}

}

package junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
import web.controller.model.OrdersQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDaoImpl {

	@Autowired
    private CustomerDao customerDao;
	
	@Autowired
    private ProductDao productDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
    private ProductsAllowedDao productsAllowedDao;
	
	@Test
    @Transactional
    public void getAllProductsAllowed() {
        try {
            List<ProductsAllowed> productsAllowed = productsAllowedDao.findAllProductsAllowed();
            for (ProductsAllowed productAllowed : productsAllowed) {
                System.out.println("ProductAllowed (All): " + productAllowed);
            }
            assertNotNull(productsAllowed);
        } catch (Exception e) {
        	System.out.println("Error JBDC" + e);
        }
    }

	@Test
    @Transactional
    public void getProductsAllowedByCustomer() {
        try {
        	Customer customer = customerDao.findCustomerById(new Customer(1));
            List<ProductsAllowed> productsAllowed = productsAllowedDao.findProductsAllowedByCustomer(customer);
            for (ProductsAllowed productAllowed : productsAllowed) {
                System.out.println("ProductAllowed (ByCustomer): " + productAllowed);
            }
            assertNotNull(productsAllowed);
        } catch (Exception e) {
        	System.out.println("Error JBDC" + e);
        }
    }
	
	@Test
    @Transactional
    public void getProductsAllowedByProduct() {
        try {
        	Product product = productDao.findProductById(new Product(1));
        	product.setName("Producto");
            List<ProductsAllowed> productsAllowed = productsAllowedDao.findProductsAllowedByProduct(product);
            for (ProductsAllowed productAllowed : productsAllowed) {
                System.out.println("ProductAllowed (ByProduct): " + productAllowed);
            }
            assertNotNull(productsAllowed);
        } catch (Exception e) {
        	System.out.println("Error JBDC" + e);
        }
    }
	
	@Test
	@Transactional
	public void getAllOrders() {
		try {
			List<Order> orders = orderDao.findAllOrders();
			for (Order order : orders) {
				System.out.println("Order (All): " + order);
			}
			assertNotNull(orders);
		} catch (Exception e) {
			System.out.println("Error JBDC" + e);
		}
	}

	@Test
	@Transactional
	public void getOrdersByCustomer() {
		try {
			Customer customer = customerDao.findCustomerById(new Customer(1));
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date initDate = formatter.parse("2017-09-21");
            Date finalDate = formatter.parse("2017-10-21");
            OrdersQuery orderQuery = new OrdersQuery(customer, initDate, finalDate);
			List<Order> orders = orderDao.findOrdersByCustomer(orderQuery);
			for (Order order : orders) {
				System.out.println("Order (ByCustomer): " + order);
			}
			assertTrue(!orders.isEmpty());
		}
        catch (ParseException e) {
        	System.out.println("Error en formato de Fecha: " + e);
        } catch (Exception e) {
			System.out.println("Error JBDC" + e);
		}
	}

	@Test
	@Transactional
	public void saveOrder() {
		Order order = this.getDefaultOrder();
		orderDao.insertOrder(order);
		System.out.println(order);
		assertNotNull(order.getId());
	}

	private Order getDefaultOrder() {
		Order order = new Order();
		order.setCustomer(customerDao.findCustomerById(new Customer(1)));
		order.setDeliveryAddress("Cra 67 N 4b-86");
		order.setCreationDate(new Date());
		List<OrderDetail> ordersDetail = new ArrayList<OrderDetail>();
		ordersDetail.add(new OrderDetail(productDao.findProductById(new Product(1)), order, 2, 10000));
		order.setOrderDetails(ordersDetail);
		return order;
	}
	
}

package junit.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
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

import data.model.Customer;
import data.model.Order;
import data.model.OrderDetail;
import data.model.Product;
import services.beans.OrderService;
import web.controller.model.OrdersQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServiceImpl {
	
	@Autowired
	OrderService orderService;
	
	@Test
    @Transactional
    public void getOrdersByCustomer() {
        try {
        	Customer customer = new Customer(1);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date initDate = formatter.parse("2017-09-21");
            Date finalDate = formatter.parse("2017-10-21");
            OrdersQuery orderQuery = new OrdersQuery(customer, initDate, finalDate);
            List<Order> orders = orderService.getOrdersByCustomer(orderQuery);
			for (Order order : orders) {
				System.out.println("Order (ByCustomer): " + order);
			}
			assertTrue(!orders.isEmpty());
        } catch (Exception e) {
        	System.out.println("Error JBDC" + e);
        }
    }

	@Test
    @Transactional
    public void createOrder() {
        try {
        	Order order = this.getDefaultOrder();
        	orderService.createOrder(order);
        	System.out.println(order);
            assertNotNull(order.getId());
        } catch (Exception e) {
        	System.out.println("Error JBDC" + e);
        }
    }

	private Order getDefaultOrder() {
		Order order = new Order();
		order.setCustomer(new Customer(1));
		order.setDeliveryAddress("Cra 67 N 4b-86");
		order.setCreationDate(new Date());
		List<OrderDetail> ordersDetail = new ArrayList<OrderDetail>();
		ordersDetail.add(new OrderDetail(new Product(1), order, 2, 10000));
		ordersDetail.add(new OrderDetail(new Product(2), order, 2, 10000));
		ordersDetail.add(new OrderDetail(new Product(4), order, 1, 10000));
		order.setOrderDetails(ordersDetail);
		return order;
	}
	
}

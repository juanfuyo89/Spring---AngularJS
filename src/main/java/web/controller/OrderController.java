package web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import data.model.Customer;
import data.model.Order;
import data.model.OrderDetail;
import services.beans.OrderService;
import services.exceptions.CustomerNotExistException;
import services.exceptions.ProductNotAllowedException;
import web.controller.model.OrdersQuery;
import web.controller.model.OrdersResponse;

/**
 * Clase que implementa el controlador REST de la aplicacion
 * 
 * @author Juan Carlos Fuyo
 *
 */
@RestController
public class OrderController {

	private static final String SUCCESS_CODE = "00";
	private static final String DATE_ERROR_CODE = "05";
	private static final String CUSTOMER_ERROR_CODE = "06";
	private static final String PRODUCT_AMOUNT_ERROR_CODE = "10";
	private static final String PRODUCT_ERROR_CODE = "12";

	@Autowired
	OrderService orderService;

	@GetMapping("getOrders/idCustomer/{idCustomer}/initDate/{initDate}/finalDate/{finalDate}")
	@ResponseBody
	public ResponseEntity getCustomers(@PathVariable("idCustomer") int idCustomer,
			@PathVariable("initDate") String initDateParam, @PathVariable("finalDate") String finalDateParam) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Order> orders;
		try {
			Customer customer = new Customer(idCustomer);
			Date initDate = formatter.parse(initDateParam);
			Date finalDate = formatter.parse(finalDateParam);
			OrdersQuery orderQuery = new OrdersQuery(customer, initDate, finalDate);
			orders = orderService.getOrdersByCustomer(orderQuery);
			return new ResponseEntity(orders, HttpStatus.OK);
		} catch (CustomerNotExistException e) {
			OrdersResponse response = OrdersResponse.getInstance(CUSTOMER_ERROR_CODE, e.getMessage());
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		} catch (ParseException e1) {
			OrdersResponse response = OrdersResponse.getInstance(DATE_ERROR_CODE, "Error en formato de fecha");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity("Error del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/createOrder")
	public ResponseEntity createCustomer(@RequestBody Order order) {
		if (!this.validateProductAmount(order)) {
			OrdersResponse response = OrdersResponse.getInstance(PRODUCT_AMOUNT_ERROR_CODE,
					"No se permiten mas de 5 productos por orden");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
		try {
			System.out.println("Orden:" + order);
			orderService.createOrder(order);
		} catch (CustomerNotExistException e) {
			OrdersResponse response = OrdersResponse.getInstance(CUSTOMER_ERROR_CODE, e.getMessage());
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} catch (ProductNotAllowedException e) {
			OrdersResponse response = OrdersResponse.getInstance(PRODUCT_ERROR_CODE, e.getMessage());
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
		OrdersResponse response = OrdersResponse.getInstance(SUCCESS_CODE, String.valueOf(order.getId()));
		return new ResponseEntity(response, HttpStatus.OK);
	}

	private boolean validateProductAmount(Order order) {
		int productAmount = 0;
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			productAmount += orderDetail.getAmount();
		}
		return (productAmount <= 5);
	}
	
}

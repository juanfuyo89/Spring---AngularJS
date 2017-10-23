package web.controller.model;

import java.io.Serializable;
import java.util.Date;

import data.model.Customer;

public class OrdersQuery implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private Date initDate;
	private Date finalDate;
	
	public OrdersQuery(Customer customer, Date initDate, Date finalDate) {
		this.customer = customer;
		this.initDate = initDate;
		this.finalDate = finalDate;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the initDate
	 */
	public Date getInitDate() {
		return initDate;
	}
	
	/**
	 * @param initDate the initDate to set
	 */
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	/**
	 * @return the finalDate
	 */
	public Date getFinalDate() {
		return finalDate;
	}

	/**
	 * @param finalDate the finalDate to set
	 */
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	@Override
	public String toString() {
		return "OrdersQuery [customer=" + customer + ", initDate=" + initDate + ", finalDate=" + finalDate + "]";
	}
	
}

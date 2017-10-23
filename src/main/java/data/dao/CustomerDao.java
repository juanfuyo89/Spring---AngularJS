package data.dao;

import java.util.List;

import data.model.Customer;

/**
 * Interface para definir el DAO de tipo Customer
 * 
 * @author Juan Carlas Fuyo
 */
public interface CustomerDao {

    /**
     * Retorna todos los clientes.
     * 
     * @return List<ProductsAllowed>
     */
	public List<Customer> findAllCustomers();

    /**
     * Retorna un cliente con base en su Id
     * 
     * @param customer
     * @return Customer
     */
	public Customer findCustomerById(Customer customer);

}

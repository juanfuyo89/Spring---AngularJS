package data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import data.dao.CustomerDao;
import data.model.Customer;

/**
 * Implementacion del Dao de tipo Customer
 * 
 * @author Juan Carlos Fuyo
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final String QUERY_ALL = "Customer.findAll";

	@PersistenceContext(unitName = "PersistenceUnit")
	private EntityManager em;
	
	public List<Customer> findAllCustomers() {
		try {
			return em.createNamedQuery(QUERY_ALL).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Customer findCustomerById(Customer customer) {
		try {
			return em.find(Customer.class, customer.getId());
		} catch (NoResultException e) {
			return null;
		}
	}

}

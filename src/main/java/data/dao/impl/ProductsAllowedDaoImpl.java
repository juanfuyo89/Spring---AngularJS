package data.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import data.dao.ProductsAllowedDao;
import data.model.Customer;
import data.model.Product;
import data.model.ProductsAllowed;

/**
 * Implementacion del Dao de tipo ProductsAllowed
 * 
 * @author Juan Carlos Fuyo
 */
@Repository
public class ProductsAllowedDaoImpl implements ProductsAllowedDao {

	private static final String QUERY_ALL = "ProductsAllowed.findAll";
	private static final String QUERY_BY_CUSTOMER = "ProductsAllowed.findByCustomer";
	private static final String QUERY_BY_PRODUCT = "ProductsAllowed.findByProduct";
	private static final String PARAM_CUSTOMER = "customer";
	private static final String PARAM_PRODUCT = "product";

	@PersistenceContext(unitName = "PersistenceUnit")
	private EntityManager em;

	public List<ProductsAllowed> findAllProductsAllowed() {
		try {
			return em.createNamedQuery(QUERY_ALL).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ProductsAllowed> findProductsAllowedByCustomer(Customer customer) {
		try {
			return em.createNamedQuery(QUERY_BY_CUSTOMER)
					.setParameter(PARAM_CUSTOMER, em.contains(customer) ? customer : em.merge(customer))
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ProductsAllowed> findProductsAllowedByProduct(Product product) {
		try {
			return em.createNamedQuery(QUERY_BY_PRODUCT)
					.setParameter(PARAM_PRODUCT, em.contains(product) ? product : em.merge(product)).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}

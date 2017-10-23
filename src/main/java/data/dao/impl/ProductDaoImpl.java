package data.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import data.dao.ProductDao;
import data.model.Product;

/**
 * Implementacion del Dao de tipo Customer
 * 
 * @author Juan Carlos Fuyo
 */
@Repository
public class ProductDaoImpl implements ProductDao {

	private static final String QUERY_ALL = "Product.findAll";

	@PersistenceContext(unitName = "PersistenceUnit")
	private EntityManager em;
	
	public List<Product> findAllProducts() {
		try {
			return em.createNamedQuery(QUERY_ALL).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Product findProductById(Product product) {
		try {
			return em.find(Product.class, product.getId());
		} catch (NoResultException e) {
			return null;
		}
	}

}

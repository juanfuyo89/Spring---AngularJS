package data.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import data.dao.OrderDao;
import data.model.Order;
import web.controller.model.OrdersQuery;

/**
 * Implementacion del Dao de tipo Order
 * 
 * @author Juan Carlos Fuyo
 */
@Repository
public class OrderDaoImpl implements OrderDao {

	private static final String QUERY_ALL = "Order.findAll";
	private static final String QUERY_BY_CUSTOMER = "Order.findByCustomer";
	private static final String PARAM_CUSTOMER = "customer";
	private static final String PARAM_INIT_DATE = "initDate";
	private static final String PARAM_FINAL_DATE = "finalDate";

	@PersistenceContext(unitName = "PersistenceUnit")
	private EntityManager em;

	public List<Order> findAllOrders() {
		try {
			return em.createNamedQuery(QUERY_ALL).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Order> findOrdersByCustomer(OrdersQuery orderQuery) {
		try {
			return em.createNamedQuery(QUERY_BY_CUSTOMER)
					.setParameter(PARAM_CUSTOMER,
							em.contains(orderQuery.getCustomer()) ? orderQuery.getCustomer()
									: em.merge(orderQuery.getCustomer()))
					.setParameter(PARAM_INIT_DATE,
							(orderQuery.getInitDate() != null) ? orderQuery.getInitDate() : new Date())
					.setParameter(PARAM_FINAL_DATE,
							(orderQuery.getFinalDate() != null) ? orderQuery.getFinalDate() : new Date())
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void insertOrder(Order order) {
		em.persist(order);
	}

}

package data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name = "products_allowed")
@NamedQueries({
		@NamedQuery(name = "ProductsAllowed.findAll", query = "SELECT p FROM ProductsAllowed p ORDER BY p.id"),
        @NamedQuery(name = "ProductsAllowed.findByCustomer", query = "SELECT p FROM ProductsAllowed p WHERE p.customer = :customer"),
        @NamedQuery(name = "ProductsAllowed.findByProduct", query = "SELECT p FROM ProductsAllowed p WHERE p.product = :product")
})
public class ProductsAllowed implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	// many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductsAllowed [customer=" + customer + ", product=" + product + "]";
	}

}

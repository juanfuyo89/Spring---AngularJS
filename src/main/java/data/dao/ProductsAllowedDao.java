package data.dao;

import java.util.List;

import data.model.Customer;
import data.model.Product;
import data.model.ProductsAllowed;

/**
 * Interface para definir el DAO de tipo ProductsAllowed
 * 
 * @author Juan Carlas Fuyo
 */
public interface ProductsAllowedDao {

    /**
     * Retorna todos los productos permitidos por cliente.
     * 
     * @return List<ProductsAllowed>
     */
	public List<ProductsAllowed> findAllProductsAllowed();

    /**
     * Retorna los productos permitidos con base en el cliente
     * 
     * @param customer
     * @return List<ProductsAllowed>
     */
	public List<ProductsAllowed> findProductsAllowedByCustomer(Customer customer);

    /**
     * Retorna los productos permitidos con base en el producto
     * 
     * @param product
     * @return List<ProductsAllowed>
     */
	public List<ProductsAllowed> findProductsAllowedByProduct(Product product);

}

package data.dao;

import java.util.List;

import data.model.Product;

/**
 * Interface para definir el DAO de tipo Product
 * 
 * @author Juan Carlas Fuyo
 */
public interface ProductDao {

    /**
     * Retorna todos los Productos.
     * 
     * @return List<ProductsAllowed>
     */
	public List<Product> findAllProducts();

    /**
     * Retorna un producto con base en su Id
     * 
     * @param product
     * @return Product
     */
	public Product findProductById(Product product);

}

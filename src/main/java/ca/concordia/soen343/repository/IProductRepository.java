package ca.concordia.soen343.repository;

import ca.concordia.soen343.model.Product;

import java.util.List;

public interface IProductRepository {

    public List<Product> getAllProducts();

    public List<Product> addProducts(int productId, int Qty);

    public List<Product> removeProducts(int productId, int Qty) throws Exception;

    public Product updateProduct(int productId, Product product);

    public Product getProductById(int productId);
}

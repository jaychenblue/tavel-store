package ca.concordia.soen343.repository;

import ca.concordia.soen343.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    private Map<Integer , Product> repo;

    public ProductRepository(){
        repo = new HashMap<>();
        repo.put(1, new Product(1, "banana", 4));
        repo.put(2, new Product(2, "apple", 3));
        repo.put(3, new Product(3, "pear", 5));
    }

    @Override
    public List<Product> addProducts(int productId, int qty) {
        int newQty = repo.get(productId).getQty() + qty;
        repo.get(productId).setQty(newQty);
        return repo.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Product> removeProducts(int productId, int qty) throws Exception {
        int newQty = repo.get(productId).getQty() - qty;
        if(newQty < 0) {
            throw new Exception("The inventory is zero. Can't reduce it");
        } else {
            repo.get(productId).setQty(newQty);
        }
        return repo.values().stream().collect(Collectors.toList());
    }

    @Override
    public Product updateProduct(int productId, Product product){
        Product pro = repo.put(productId, product);
        return pro;
    }

    @Override
    public List<Product> getAllProducts(){
        return repo.values().stream().collect(Collectors.toList());
    }

    @Override
    public Product getProductById(int productId){
        return repo.values().stream().filter(prod -> prod.getId() == productId).collect(Collectors.toList()).get(0);
    }
}

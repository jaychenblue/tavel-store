package ca.concordia.soen343.controller;

import ca.concordia.soen343.model.Product;
import ca.concordia.soen343.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements IProductController {

    @Autowired
    private IProductRepository productRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    @RequestMapping(path = "/{productId}/{qty}", method = RequestMethod.PUT)
    public List<Product> addProducts(
            @PathVariable int productId, @PathVariable int qty){
    		
        return productRepository.addProducts(productId, qty);
    }

    @RequestMapping(path = "/delete/{productId}/{qty}", method = RequestMethod.DELETE)
    public List<Product> deleteProducts(@PathVariable int productId, @PathVariable int qty) throws Exception {
        return productRepository.removeProducts(productId, qty);
    }
}

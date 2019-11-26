package ca.concordia.soen343.controller;

import ca.concordia.soen343.model.Cart;
import ca.concordia.soen343.model.CartItem;
import ca.concordia.soen343.repository.ICartRepository;
import ca.concordia.soen343.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController implements ICartController {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Cart getCartById(@PathVariable int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    public Cart createCart(@PathVariable int userId) {
        return cartRepository.createCart(userId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public Cart addItemToCart(@PathVariable int userId, @RequestBody CartItem item) throws Exception {
        if(productRepository.getProductById(item.getProductId()).getQty() < item.getQty()){
            throw  new Exception("Not enough Items");
        }
        return cartRepository.addItem(userId, item);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public Cart deleteItemFromCart(@PathVariable int userId, @RequestBody CartItem item){
        return cartRepository.removeItem(userId, item);
    }

    @RequestMapping(path = "/{userId}/{productId}/{qty}", method = RequestMethod.DELETE)
    public Cart removeQty(@PathVariable int userId, @PathVariable int productId, @PathVariable int qty) throws Exception {
        if(cartRepository.getCartItemQty(userId, productId).getQty() < qty ){
        throw new Exception("not enough items to be removed");
        }
        return cartRepository.removeQty(userId, productId, qty);
    }
}

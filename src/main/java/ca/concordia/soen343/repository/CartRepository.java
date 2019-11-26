package ca.concordia.soen343.repository;

import ca.concordia.soen343.model.Cart;
import ca.concordia.soen343.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CartRepository implements ICartRepository {

    private Map<Integer, Cart> repo;

    public CartRepository() {
        this.repo = new HashMap<>();
        repo.put(1234, new Cart(1234, new ArrayList<CartItem>()));
    }


    @Override
    public Cart createCart(int userId) {
        repo.put(userId, new Cart(userId, new ArrayList<>()));
        return repo.get(userId);
    }

    @Override
    public Cart getCartByUserId(int userId) {
        return repo.get(userId);
    }

    @Override
    public Cart deleteCartByUserId(int userId) {
        return repo.remove(userId);
    }

    @Override
    public Cart addItem(int userId, CartItem item) {
        repo.get(userId).getItemsList().add(item);
        return repo.get(userId);
    }

    @Override
    public Cart addItemByQty(int userId, int productId, int qty) {
        List<CartItem> items = repo.get(userId).getItemsList();
        List<Integer> ids = items.stream().map(item -> item.getProductId()).collect(Collectors.toList());
        int index = ids.indexOf(productId);
        int newQty = items.get(index).getQty() + qty;
        items.get(index).setQty(newQty);
        return repo.get(userId);
    }

    @Override
    public Cart removeItem(int userId, CartItem item) {
        List<CartItem> newList = repo.get(userId).getItemsList().stream().filter(itemInList -> itemInList.getProductId() != item.getProductId()).collect(Collectors.toList());
        repo.get(userId).setItemsList(newList);
        return repo.get(userId);
    }

    @Override
    public Cart removeQty(int userId, int productId, int qty) {
        List<CartItem> items = repo.get(userId).getItemsList();
        List<Integer> ids = items.stream().map(item -> item.getProductId()).collect(Collectors.toList());
        int index = ids.indexOf(productId);
        int newQty = items.get(index).getQty() - qty;
        items.get(index).setQty(newQty);
        return repo.get(userId);
    }

    public CartItem getCartItemQty(int userId, int productId) {
        return getCartByUserId(userId).getItemsList().stream().filter(i -> i.getProductId() == productId).collect(Collectors.toList()).get(0);
    }
}


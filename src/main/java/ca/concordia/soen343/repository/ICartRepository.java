package ca.concordia.soen343.repository;


import ca.concordia.soen343.model.Cart;
import ca.concordia.soen343.model.CartItem;

public interface ICartRepository {

    public Cart createCart(int userId);

    public Cart getCartByUserId(int userId);

    public Cart deleteCartByUserId(int userId);

    public Cart addItem(int userId, CartItem item);

    public Cart addItemByQty(int userId, int productId, int qty);

    public Cart removeItem(int userId, CartItem item);

    public Cart removeQty(int userId, int productId, int qty);

    public CartItem getCartItemQty(int userId, int productId);
}

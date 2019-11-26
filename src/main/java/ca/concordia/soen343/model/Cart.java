package ca.concordia.soen343.model;


import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    private int userId;

    private List<CartItem> itemsList;

    public Cart(int userId, List<CartItem> itemsList){
        this.userId = userId;
        this.itemsList = itemsList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<CartItem> itemsList) {
        this.itemsList = itemsList;
    }
}

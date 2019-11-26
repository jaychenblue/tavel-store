package ca.concordia.soen343.model;

public class CartItem {

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    private int productId;

    private int Qty;

    public CartItem(int productId, int Qty){
        this.productId = productId;
        this.Qty = Qty;
    }
}

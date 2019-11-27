package ca.concordia.soen343.model;

import org.springframework.stereotype.Component;

public class Product {

    private int id;

    private String name;

    private int Qty;

    public Product(int id, String name, int qty){
        this.id = id;
        this.name = name;
        this.Qty = qty;
    }


    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

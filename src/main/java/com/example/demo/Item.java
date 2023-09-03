package com.example.demo;

public class Item {

    private double price;
    private int quantity;
    private String description;

    public Item(String description, double price, int qty) {
        this.price = price;
        this.quantity = qty;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

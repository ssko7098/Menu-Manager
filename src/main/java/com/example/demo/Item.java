package com.example.demo;

public class Item {

    private double price;
    private double quantity;
    private String name;
    private String description;

    public Item(String name, String description, double price) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Item(String name, String description, double price, int quantity) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }
}


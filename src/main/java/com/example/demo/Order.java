package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

    private int orderID;
    private double total = 0;
    private ArrayList<Item> items;
    private String date;

    public Order(int id, ArrayList<Item> items, String date) {
        this.date = date;
        this.orderID = id;
        this.items = items;
        this.total = updateTotal();
    }

    public double updateTotal() {
        for(int i=0; i<items.size(); i++) {
            this.total += items.get(i).getPrice();
        }

        return this.total;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotal() {
        return this.total;
    }

    public long getOrderID() {
        return orderID;
    }

    public String getDate() {
        return date;
    }

    public String getItems() {
        String result = "";

        for(int i=0; i<this.items.size(); i++) {
            if (i > 0) {
                result = result + ", " + items.get(i).getName();
            }
            else {
                result = result + items.get(i).getName();
            }
        }

        return result;
    }
}
package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class MenuTesterFile {
    @Start
    public void start(Stage primaryStage) throws IOException {
        Stage stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("afterLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.resizableProperty();
        stage.setTitle("Menu Manager");
        stage.setScene(scene);
        stage.show();
//        stage.close();
    }


    @Test
    void descTest(){
        Item item = new Item("bob", "bob is cool", 4.8);
        item.setDescription("bob is not cool");
        String a = item.getDescription();
        assertEquals(a, "bob is not cool");
    }

    @Test
    void nameTest(){
        Item item = new Item("bob", "bob is cool", 4.8);
        item.setName("david");
        String a = item.getName();
        assertEquals(a, "david");
    }

    @Test
    void priceTest(){
        Item item = new Item("bob", "bob is cool", 4.8);
        item.setPrice(2.11d);
        double a = item.getPrice();
        assertEquals(a, 2.11, 0.001);
    }

    @Test
    void orderTest(){
        Item item1 = new Item("bob", "bob is cool", 4.8);
        Item item2 = new Item("burger", "burger is tasty", 14.8);
        Item item3 = new Item("pizza", "pizza is crispy", 34.6);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
        Order order = new Order(2, items, "July 1st");
        order.addItem(item3);
        double t = order.getTotal();
        long i = order.getOrderID();
        String s = order.getItems();
        String d = order.getDate();
    }

    @Test
    void orderHistoryTest() throws IOException, ParseException {
        OrderHistory o = new OrderHistory();
        o.updateOrders();
    }

    @Test
    void orderHistoryTest2() throws IOException, ParseException {
        Item item1 = new Item("bob", "bob is cool", 4.8);
        Item item2 = new Item("burger", "burger is tasty", 14.8);
        Item item3 = new Item("pibzza", "pizza is crispy", 34.6);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
        Order order = new Order(2, items, "July 1st");
        order.addItem(item3);
        OrderHistory o = new OrderHistory();
        o.updateOrders();
        o.updateOrders();
//        o.searchOrder();
    }

}

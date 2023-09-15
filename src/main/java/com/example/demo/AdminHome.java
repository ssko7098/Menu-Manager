package com.example.demo;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AdminHome implements Initializable {

    @FXML
    private Button logOut;

    @FXML
    private Button menuButton;

    @FXML
    private Label totalOrders;

    @FXML
    private Label totalAmount;

    public void logOut() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    public void viewMenu() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("menu.fxml");
    }

    public void viewOrderHistory() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("OrderHistory.fxml");
    }

    public void createAdmin() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("SignUp.fxml");
    }

    public void goHome() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("afterLogin.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int orderCount = 0;
        double total = 0;

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("orders.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray orders = (JSONArray) jsonObject.get("Orders");
            orderCount = orders.size();

            for(int i=0; i<orders.size(); i++) {
                JSONObject order = (JSONObject) orders.get(i);
                JSONArray items = (JSONArray) order.get("items");

                for(int x=0; x<items.size(); x++) {
                    JSONObject item = (JSONObject) items.get(x);
                    Double price = Double.parseDouble(item.get("price").toString());
                    int qty = Integer.parseInt(item.get("quantity").toString());

                    total += (price*qty);
                }

            }

            totalOrders.setText(Integer.toString(orderCount));
            totalAmount.setText("$" + total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

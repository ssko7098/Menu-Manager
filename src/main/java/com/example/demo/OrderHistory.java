package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderHistory implements Initializable {

    @FXML
    private TextField orderNumber;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, Integer> orderID;

    @FXML
    private TableColumn<Order, LocalDate> date;

    @FXML
    private TableColumn<Order, String> items;

    @FXML
    private TableColumn<Order, Double> total;

    public ObservableList<Order> list = FXCollections.observableArrayList();

    public void logOut() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    public void viewOrders() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("menu.fxml");
    }

    public void updateOrders() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("orders.json"));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray orders = (JSONArray) jsonObject.get("Orders");

        for(int i=0; i<orders.size(); i++) {
            JSONObject order = (JSONObject) orders.get(i);
            JSONArray items = (JSONArray) order.get("items");
            String date = order.get("date").toString();

            ArrayList<Item> ls = new ArrayList<>();

            for(int x=0; x<items.size(); x++) {
                JSONObject item = (JSONObject) items.get(x);
                String name = item.get("name").toString();
                int qty = Integer.parseInt(item.get("quantity").toString());
                double price = Double.parseDouble(item.get("price").toString());

                ls.add(new Item((qty + "x " + name), price*qty, qty));
            }

            list.add(new Order(i+1, ls, date));
        }
    }

    public void viewMenu() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("menu.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderID"));
        date.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("Date"));
        items.setCellValueFactory(new PropertyValueFactory<Order, String>("Items"));
        total.setCellValueFactory(new PropertyValueFactory<Order, Double>("Total"));

        try {
            updateOrders();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        table.setItems(list);
    }

    public void searchOrder() throws IOException, ParseException {
        if(orderNumber.getText().isEmpty()) {
            HelloApplication m = new HelloApplication();
            m.changeScene("OrderHistory.fxml");
            return;
        }

        table.getItems().clear();

        orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderID"));
        date.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("Date"));
        items.setCellValueFactory(new PropertyValueFactory<Order, String>("Items"));
        total.setCellValueFactory(new PropertyValueFactory<Order, Double>("Total"));

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("orders.json"));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray orders = (JSONArray) jsonObject.get("Orders");

        for(int i=0; i<orders.size(); i++) {
            if(i+1 == Integer.parseInt(orderNumber.getText())) {
                JSONObject order = (JSONObject) orders.get(i);
                JSONArray items = (JSONArray) order.get("items");
                String date = order.get("date").toString();

                ArrayList<Item> ls = new ArrayList<>();

                for(int x=0; x<items.size(); x++) {
                    JSONObject item = (JSONObject) items.get(x);
                    String name = item.get("name").toString();
                    int qty = Integer.parseInt(item.get("quantity").toString());
                    double price = Double.parseDouble(item.get("price").toString());

                    ls.add(new Item((qty + "x " + name), price*qty, qty));
                }

                list.add(new Order(i+1, ls, date));
            }

        }

        table.setItems(list);
    }

    public void goHome() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("afterLogin.fxml");
    }


    public void createAdmin() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("SignUp.fxml");
    }


}

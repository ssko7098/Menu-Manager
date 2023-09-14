package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.DecimalFormat;


public class Checkout implements Initializable {

    @FXML
    private TextField Address;

    @FXML
    private TextField CVC;

    @FXML
    private TextField Card;

    @FXML
    private Button ChangeItems;

    @FXML
    private TextField Expiry;

    @FXML
    private TextField Name;

    @FXML
    private Button Payment;

    @FXML
    private TextField Phone;

    @FXML
    private TextField PostCode;

    @FXML
    private TableView<Item> checkoutTable;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, Integer> quantity;

    @FXML
    private TextField State;

    @FXML
    private TableColumn<Item, String> item;
    public ObservableList<Item> itemInfo = FXCollections.observableArrayList();

    @FXML
    private Label totalPrice;




    @FXML
    void toMenu(ActionEvent event) throws IOException {
        HelloApplication changeOrder = new HelloApplication();
        changeOrder.changeScene("initialMenu.fxml");
        //addToHistory();
    }

    @FXML
    void toCompletion(ActionEvent event) throws IOException, ParseException {
        HelloApplication toEnd = new HelloApplication();
        toEnd.changeScene("completion.fxml");
        addToHistory();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        try {
            displayCheckout();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        checkoutTable.setItems(itemInfo);
    }

    public void displayCheckout() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object cart = parser.parse(new FileReader("cart.json"));
        JSONObject itemsList = (JSONObject) cart;
        JSONArray items = (JSONArray) itemsList.get("Cart");
        double qty = 0;
        for (Object o : items) {
            JSONObject item = (JSONObject) o;
            itemInfo.add(new Item(item.get("name").toString(), Double.parseDouble(item.get("price").toString()), Integer.parseInt(item.get("quantity").toString())));
            qty += Double.parseDouble(item.get("price").toString()) * Integer.parseInt(item.get("quantity").toString());
        }
        DecimalFormat df = new DecimalFormat("##.00");
        totalPrice.setText("$" + df.format(qty));
    }

    public void addToHistory() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject orderHistory = (JSONObject) parser.parse(new FileReader("orders.json"));

        JSONArray orders = (JSONArray) orderHistory.get("Orders");

        JSONObject orderDetails = new JSONObject();
        orderDetails.put("date", java.time.LocalDate.now().toString());
        JSONArray itemDetails = new JSONArray();
        for (int j = 0; j < checkoutTable.getItems().size(); j++) {

            JSONObject itemNew = new JSONObject();

            itemNew.put("name", item.getCellData(j));
            itemNew.put("price", price.getCellData(j));
            itemNew.put("quantity", quantity.getCellData(j));
            itemDetails.add(itemNew);
        }
        orderDetails.put("items", itemDetails);
        orders.add(orderDetails);


        orderHistory.put("Orders", orders);

        FileWriter file = new FileWriter("orders.json");
        file.write(orderHistory.toJSONString());
        file.flush();
        file.close();
    }
}
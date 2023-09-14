package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, Integer> quantity;

    @FXML
    private TextField State;

    @FXML
    private TableColumn<Item, String> item;

    public ObservableList<Item> itemData = FXCollections.observableArrayList();


    @FXML
    void toMenu(ActionEvent event) throws IOException {
        HelloApplication changeOrder = new HelloApplication();
        changeOrder.changeScene("initialMenu.fxml");
    }

    @FXML
    void toCompletion(ActionEvent event) throws IOException {
        HelloApplication toEnd = new HelloApplication();
        toEnd.changeScene("completion.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        //table.setEditable(true);
        item.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        //table.setItems(itemData);
    }
    public ObservableList<Item> displayCheckout() throws IOException, ParseException {


        JSONParser parser = new JSONParser();
        Object cart = parser.parse(new FileReader("cart.json"));
        System.out.println(cart.toString());
        //convert Object to JSONObject
        //JSONObject Items = (JSONObject) cart;
        JSONArray Items = (JSONArray) cart;
        //JSONArray names = (JSONArray) jsonObject.get("name");

        for (int i = 0; i < Items.size(); i++) {
            JSONObject item = (JSONObject) Items.get(i);
            itemData.add(new Item(item.get("name").toString(), Integer.parseInt(item.get("price").toString()), Double.parseDouble(item.get("quantity").toString())));
        }
        table.setItems(itemData);
        return itemData;
    }

}


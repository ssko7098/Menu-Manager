package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitialMenu implements Initializable {

    @FXML
    private Button cartButton;
    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> itemColumn;

    @FXML
    private TableColumn<Item, String> categoryColumn;

    @FXML
    private TableColumn<Item, Double> quantityColumn;

    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, String> descriptionColumn;


    public void goToCart() throws IOException {

        HelloApplication viewCart = new HelloApplication();
        viewCart.changeScene("checkout.fxml");
    }


    public void initialize(URL location, ResourceBundle resources) {
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("Quantity"));
        try {
            table.setItems(displayItems());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Item> displayItems() throws IOException, ParseException {

        ObservableList<Item> itemData = FXCollections.observableArrayList();

        JSONParser parser = new JSONParser();
        JSONArray menu = (JSONArray) parser.parse(new FileReader("menu.json"));

        for (int i=0; i<menu.size(); i++) {
            JSONObject item = (JSONObject) menu.get(i);
            itemData.add(new Item(item.get("name").toString(), item.get("description").toString(), Double.parseDouble(item.get("price").toString())));
        }

        table.setItems(itemData);
        return itemData;
    }

}

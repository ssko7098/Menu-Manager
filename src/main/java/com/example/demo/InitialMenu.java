package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitialMenu implements Initializable {

    @FXML
    private Button cartButton;

    @FXML
    public TableView<Item> table;

    @FXML
    private TableColumn<Item, String> itemColumn;

    @FXML
    private TableColumn<Item, String> categoryColumn;

    @FXML
    private TableColumn<Item, Integer> quantityColumn;

    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, String> descriptionColumn;


    public void goToCart() throws IOException {

        HelloApplication viewCart = new HelloApplication();
        viewCart.changeScene("checkout.fxml");
    }


    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, Integer> event) {
                Item item = event.getRowValue();
                item.setQuantity(event.getNewValue());
            }
        });
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

        for (int i = 0; i < menu.size(); i++) {
            JSONObject item = (JSONObject) menu.get(i);
            itemData.add(new Item(item.get("name").toString(), item.get("description").toString(), Double.parseDouble(item.get("price").toString()), 1));

        }
        table.setItems(itemData);
        return itemData;
    }

//    public void addOrder() throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        JSONArray cart = (JSONArray) parser.parse(new FileReader("cart.json"));
//
//        JSONObject itemNew = new JSONObject();
//
//        for(int i = 0; i < table.) {
//
//        }
//        table.getColumns().get(0).getCellObservableValue(0).getValue().toString();
//
//        // iterate through table, if quantity > 0, put name, price, quantity
//        itemNew.put("name", name.getText());
//        itemNew.put("price", newPrice.getText());
//        itemNew.put("quantity", quantity.getInt());
//
//        cart.add(itemNew);
//
//        FileWriter file = new FileWriter("cart.json");
//        file.write(menu.toJSONString());
//        file.flush();
//        file.close();
//
//    }

}
}

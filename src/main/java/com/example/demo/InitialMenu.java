package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InitialMenu implements Initializable {

    private Map<String, Integer> itemQuantities = new HashMap<>();


    @FXML
    private Button cartButton;

    @FXML
    public TableView<Item> table;

    @FXML
    private TableColumn<Item, String> itemColumn;

    @FXML
    private ChoiceBox<String> currentCat;

    @FXML
    private TableColumn<Item, Integer> quantityColumn;

    @FXML
    private TableColumn<Item, Double> priceColumn;

    @FXML
    private TableColumn<Item, String> descriptionColumn;

    @FXML
    public ObservableList<String> availableCats = FXCollections.observableArrayList("Mains", "Desserts");


    public void goToCart() throws IOException {

        HelloApplication viewCart = new HelloApplication();
        viewCart.changeScene("checkout.fxml");
    }

    public void goBack() throws IOException {

        HelloApplication viewCart = new HelloApplication();
        viewCart.changeScene("hello-view.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        currentCat.setValue("Mains");
        currentCat.setItems(availableCats);

        itemColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));

        try {
            for (Item item : displayItems()) {
                itemQuantities.put(item.getName(), 0);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        quantityColumn.setCellValueFactory(cellData -> {
            String itemName = cellData.getValue().getName();
            int quantity = itemQuantities.getOrDefault(itemName, 0);
            return new SimpleIntegerProperty(quantity).asObject();
        });

        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        quantityColumn.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setQuantity(event.getNewValue());
            itemQuantities.put(item.getName(), event.getNewValue()); // Update the quantity in the data structure
        });

        changeMenu();
    }



    public ObservableList<Item> displayItems() throws IOException, ParseException {

        ObservableList<Item> itemData = FXCollections.observableArrayList();

        JSONParser parser = new JSONParser();

        Object menu = parser.parse(new FileReader("menu.json"));
        JSONObject jsonObject = (JSONObject) menu;
        JSONArray catMenu = (JSONArray) jsonObject.get(currentCat.getValue());

        for (int i = 0; i < catMenu.size(); i++) {
                JSONObject item = (JSONObject) catMenu.get(i);
                itemData.add(new Item(item.get("name").toString(), item.get("description").toString(), Double.parseDouble(item.get("price").toString())));
            }


        table.setItems(itemData);
            return itemData;
        }


    public void addOrder() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader("cart.json"));

        JSONArray cart = new JSONArray();
        for (int i = 0; i < table.getItems().size(); i++) {
            if (quantityColumn.getCellData(i) > 0) {
                JSONObject itemNew = new JSONObject();

                itemNew.put("name", itemColumn.getCellData(i));
                itemNew.put("price", priceColumn.getCellData(i));
                itemNew.put("quantity", quantityColumn.getCellData(i));

                cart.add(itemNew);
            }
        }


        obj.put("Cart", cart);

        FileWriter file = new FileWriter("cart.json");
        file.write(obj.toJSONString());
        file.flush();
        file.close();
    }

    public void changeMenu() {
        currentCat.setOnAction(event -> {
            try {
                table.setItems(displayItems());

//                table.refresh(); // Refresh the table to reflect the updated quantities
                this.displayItems();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }



}









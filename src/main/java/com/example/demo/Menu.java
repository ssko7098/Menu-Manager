package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> description;

    @FXML
    private TableColumn<Item, Integer> quantity;

    @FXML
    private TableColumn<Item, Double> price;

    public ObservableList<Item> list = FXCollections.observableArrayList();

    public void logOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    public void updateMenu() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray items = (JSONArray) parser.parse(new FileReader("menu.json"));

        for (int i=0; i<items.size(); i++) {
            JSONObject item = (JSONObject) items.get(i);

            list.add(new Item(item.get("item").toString(), Double.parseDouble(item.get("price").toString()), 1));

            System.out.println(item.get("item") + ": " + item.get("price"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        description.setCellValueFactory(new PropertyValueFactory<Item, String>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<Item, Double>("Price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Quantity"));

        try {
            updateMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        table.setItems(list);
    }
}

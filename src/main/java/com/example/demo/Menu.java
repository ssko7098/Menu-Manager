package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> name;

    @FXML
    private TableColumn<Item, String> description;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private Button AddItem;

    @FXML
    private Button removeItem;

    @FXML
    private Button updateItem;

    @FXML
    private TextField updatedName;

    @FXML
    private TextField updatedDescription;

    @FXML
    private TextField updatedPrice;

    @FXML
    private TextField newItem;

    @FXML
    private TextField newPrice;

    @FXML
    private TextField newDescription;

    @FXML
    private TextField removedItem;

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

            list.add(new Item(item.get("name").toString(), item.get("description").toString(), Double.parseDouble(item.get("price").toString())));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        description.setCellValueFactory(new PropertyValueFactory<Item, String>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<Item, Double>("Price"));

        try {
            updateMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        table.setItems(list);
    }

    public void addItem() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        JSONParser parser = new JSONParser();
        JSONArray menu = (JSONArray) parser.parse(new FileReader("menu.json"));

        JSONObject itemNew = new JSONObject();

        for (int i=0; i<menu.size(); i++) {
            JSONObject item = (JSONObject) menu.get(i);

            if(item.get("name").equals(newItem.getText())) {
                return;
            }
        }

        itemNew.put("name", newItem.getText());
        itemNew.put("description", newDescription.getText());
        itemNew.put("price", newPrice.getText());

        menu.add(itemNew);

        FileWriter file = new FileWriter("menu.json");
        file.write(menu.toJSONString());
        file.flush();
        file.close();

        m.changeScene("menu.fxml");
    }

    public void removeItem() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        JSONParser parser = new JSONParser();
        JSONArray menu = (JSONArray) parser.parse(new FileReader("menu.json"));

        for (int i=0; i<menu.size(); i++) {
            JSONObject item = (JSONObject) menu.get(i);

            if(item.get("name").equals(removedItem.getText())) {
                menu.remove(item);
            }
        }

        FileWriter file = new FileWriter("menu.json");
        file.write(menu.toJSONString());
        file.flush();
        file.close();

        m.changeScene("menu.fxml");
    }

    public void updateItem() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        JSONParser parser = new JSONParser();
        JSONArray menu = (JSONArray) parser.parse(new FileReader("menu.json"));

        for (int i=0; i<menu.size(); i++) {
            JSONObject item = (JSONObject) menu.get(i);

            if(item.get("name").equals(updatedName.getText())) {
                if(!updatedDescription.getText().isEmpty()) {
                    item.replace("description", updatedDescription.getText());
                }
                if(!updatedPrice.getText().isEmpty()) {
                    item.replace("price", Double.parseDouble(updatedPrice.getText()));
                }

            }
        }

        FileWriter file = new FileWriter("menu.json");
        file.write(menu.toJSONString());
        file.flush();
        file.close();

        m.changeScene("menu.fxml");
    }

    public void viewOrderHistory(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("OrderHistory.fxml");
    }

    public void createAdmin(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("SignUp.fxml");
    }
}

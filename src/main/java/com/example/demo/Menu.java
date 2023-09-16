package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    public TableView<Item> table;

    @FXML
    public TableColumn<Item, String> name;

    @FXML
    public TableColumn<Item, String> description;

    @FXML
    public TableColumn<Item, Double> price;

    @FXML
    public Button AddItem;

    @FXML
    public Button removeItem;

    @FXML
    public Button updateItem;

    @FXML
    public TextField updatedName;

    @FXML
    public TextField updatedDescription;

    @FXML
    public TextField updatedPrice;

    @FXML
    public TextField newItem;

    @FXML
    public TextField newPrice;

    @FXML
    public TextField newDescription;

    @FXML
    public TextField removedItem;

    @FXML
    public ChoiceBox<String> currentCat;

    public ObservableList<Item> list = FXCollections.observableArrayList();

    public ObservableList<String> availableCats = FXCollections.observableArrayList("Mains", "Desserts");

    @FXML
    private Button logout;
    @FXML
    private Button menuButtonMenu;
    @FXML
    private Button newAdminUserButtonMenu;
    @FXML
    private Button orderHistoryButtonMenu;
    @FXML
    private Button adminHomeButtonMenu;

    public void clearFields() {
        updatedName.clear();
        updatedDescription.clear();
        updatedPrice.clear();
        newItem.clear();
        newPrice.clear();
        newDescription.clear();
        removedItem.clear();
    }

    public void logOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    public void updateMenu() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object menu = parser.parse(new FileReader("menu.json"));

        // convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) menu;

        // reading the "Mains" array:
        JSONArray mains = (JSONArray) jsonObject.get(currentCat.getValue());

        for (int i=0; i<mains.size(); i++) {
            JSONObject item = (JSONObject) mains.get(i);

            list.add(new Item(item.get("name").toString(), item.get("description").toString(), Double.parseDouble(item.get("price").toString())));
        }
        clearFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentCat.setValue("Mains");
        currentCat.setItems(availableCats);

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
        Object menu = parser.parse(new FileReader("menu.json"));

        // convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) menu;

        JSONArray catMenu = (JSONArray) jsonObject.get(currentCat.getValue());

        JSONObject itemNew = new JSONObject();

        for (int i=0; i<catMenu.size(); i++) {
            JSONObject item = (JSONObject) catMenu.get(i);

            if(item.get("name").equals(newItem.getText())) {
                return;
            }
        }

        itemNew.put("name", newItem.getText());
        itemNew.put("description", newDescription.getText());
        itemNew.put("price", newPrice.getText());

        catMenu.add(itemNew);
        jsonObject.put(currentCat.getValue(), catMenu);

        FileWriter file = new FileWriter("menu.json");
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();

        try {
            table.getItems().clear();
            this.updateMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeItem() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();

        JSONParser parser = new JSONParser();
        Object menu = parser.parse(new FileReader("menu.json"));

        // convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) menu;

        JSONArray catMenu = (JSONArray) jsonObject.get(currentCat.getValue());

        for (int i=0; i<catMenu.size(); i++) {
            JSONObject item = (JSONObject) catMenu.get(i);

            if(item.get("name").equals(removedItem.getText())) {
                catMenu.remove(item);
            }
        }

        FileWriter file = new FileWriter("menu.json");
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();

        try {
            table.getItems().clear();
            this.updateMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateItem() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();

        JSONParser parser = new JSONParser();
        Object menu = parser.parse(new FileReader("menu.json"));

        // convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) menu;

        JSONArray catMenu = (JSONArray) jsonObject.get(currentCat.getValue());

        for (int i=0; i<catMenu.size(); i++) {
            JSONObject item = (JSONObject) catMenu.get(i);

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
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();

        try {
            table.getItems().clear();
            this.updateMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewOrderHistory(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("OrderHistory.fxml");
    }

    public void createAdmin(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("SignUp.fxml");
    }

    public void goHome(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("afterLogin.fxml");
    }

    public void changeMenu() {
        currentCat.setOnAction((event) -> {
            try {
                table.getItems().clear();
                this.updateMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

package com.example.demo;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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
    private TableColumn<?, ?> Price;

    @FXML
    private TableColumn<?, ?> Quantity;

    @FXML
    private TextField State;

    @FXML
    private TableColumn<?, ?> item;

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

    }
}


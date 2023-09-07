package com.example.demo;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Checkout {

    @FXML
    private TextField Address;

    @FXML
    private Button ChangeItems;

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
    void toMenu(ActionEvent event) {

        }

}


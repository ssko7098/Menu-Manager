package com.example.demo;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;


public class AfterLogin {

    @FXML
    private Button logout;


    public void logOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    public void viewMenu(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("menu.fxml");
    }

    public void viewOrderHistory(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("OrderHistory.fxml");
    }

}

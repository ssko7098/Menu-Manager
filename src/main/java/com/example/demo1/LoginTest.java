package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginTest {

    public void userLogin() throws IOException {
        test();
    }

    @FXML
    private Button button;

    @FXML
    private Label wrongLogin;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void test() throws IOException {
        HelloApplication m = new HelloApplication();
        if (username.getText().toString().equals("sebskontos") && password.getText().toString().equals("1234")) {
            wrongLogin.setText("Success!");

            m.changeScene("afterLogin.fxml");
        }
        else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your data.");
        }
        else {
            wrongLogin.setText("Wrong username or password!");
        }

    }

}

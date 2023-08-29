package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginTest {

    public void userLogin() throws IOException, ParseException {
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

    public void test() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        if (checkAdmin(username.getText().toString(), password.getText().toString())) {
            wrongLogin.setText("Success!");

            m.changeScene("afterLogin.fxml");
        }
        else {
            wrongLogin.setText("Wrong username or password!");
        }

    }

    public boolean checkAdmin(String username, String password) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray admins = (JSONArray) parser.parse(new FileReader("admin.json"));

        for (int i=0; i<admins.size(); i++) {
            JSONObject admin = (JSONObject) admins.get(i);

            if (admin.get("username").equals(username) && admin.get("password").equals(password)) {
                return true;
            }
        }
        return false;
    }

}

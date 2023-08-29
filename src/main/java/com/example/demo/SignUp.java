package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp {

    @FXML
    private Button button;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    public void createAdmin() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        JSONParser parser = new JSONParser();
        JSONArray admins = (JSONArray) parser.parse(new FileReader("admin.json"));

        JSONObject newAdmin = new JSONObject();
        newAdmin.put("username", username.getText().toString());
        newAdmin.put("password", password.getText().toString());

        admins.add(newAdmin);

        FileWriter file = new FileWriter("admin.json");
        file.write(admins.toJSONString());
        file.flush();
        file.close();

        m.changeScene("hello-view.fxml");
    }
}

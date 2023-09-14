package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginTest {

    public void userLogin() throws IOException, ParseException {
        test();
    }

    @FXML
    public Button button;

    @FXML
    public Label wrongLogin;

    @FXML
    public TextField username;

    @FXML
    public TextField passwordText;

    @FXML
    public PasswordField password;

    @FXML
    public ImageView showPassword;

//    public Image hideImage = new Image("/com/example/demo/loginImages/hide.png");
//    public Image showImage = new Image("/com/example/demo/loginImages/visible.png");

    public void test() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        if (username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your username and password");
        }

        else if (checkAdmin(username.getText(), password.getText())) {
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

    public void goToOrder() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("initialMenu.fxml");
    }
//    public void changeVisibility(){
//
//        showPassword.setImage(showImage);
//        passwordText.setText(password.getText());
//        passwordText.setVisible(true);
//        password.setVisible(false);
//
//
//        //System.out.println("image clicked");
//    }
//    public void changeVisibilityImage(){
//
//        showPassword.setImage(hideImage);
//        password.setText(passwordText.getText());
//        passwordText.setVisible(false);
//        password.setVisible(true);
//        //System.out.println("image released");
//    }

}

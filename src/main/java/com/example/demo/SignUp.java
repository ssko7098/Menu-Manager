package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView showPassword;

    @FXML
    private TextField passwordText;

    @FXML
    private Label alreadyExists;

    Image hideImage = new Image("/com/example/demo/loginImages/hide.png");
    Image showImage = new Image("/com/example/demo/loginImages/visible.png");

    public void createAdmin() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        JSONParser parser = new JSONParser();
        JSONArray admins = (JSONArray) parser.parse(new FileReader("admin.json"));

        JSONObject newAdmin = new JSONObject();

        if (checkAdmin(username.getText().toString())) {
            alreadyExists.setText("This user already exists");
            return;
        } else if (username.getText().toString() == "") {
            alreadyExists.setText("Username Empty");
            return;
        } else if (password.getText().toString() == "") {
            alreadyExists.setText("Password Empty");
            return;
        }


        newAdmin.put("username", username.getText().toString());
        newAdmin.put("password", password.getText().toString());

        admins.add(newAdmin);

        FileWriter file = new FileWriter("admin.json");
        file.write(admins.toJSONString());
        file.flush();
        file.close();

        m.changeScene("hello-view.fxml");
    }

    public boolean checkAdmin(String username) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray admins = (JSONArray) parser.parse(new FileReader("admin.json"));

        for (int i=0; i<admins.size(); i++) {
            JSONObject admin = (JSONObject) admins.get(i);

            if (admin.get("username").equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void back() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("afterLogin.fxml");
    }

    public void changeVisibility(){

        showPassword.setImage(showImage);
        passwordText.setText(password.getText());
        passwordText.setVisible(true);
        password.setVisible(false);


        //System.out.println("image clicked");
    }
    public void changeVisibilityImage(){

        showPassword.setImage(hideImage);
        password.setText(passwordText.getText());
        passwordText.setVisible(false);
        password.setVisible(true);
        //System.out.println("image released");
    }
}

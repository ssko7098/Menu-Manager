package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest {

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

    @Test
    void loadImagesTest(){
        Image hideImage = new Image("/com/example/demo/loginImages/hide.png");
        Image showImage = new Image("/com/example/demo/loginImages/visible.png");
        assertEquals("/com/example/demo/loginImages/hide.png",hideImage.getUrl());
        assertEquals("/com/example/demo/loginImages/visible.png",showImage.getUrl());
    }

    @Test
    void testTest(){
        SignUp singUp = new SignUp();
        singUp.tes
        username.set
    }

}

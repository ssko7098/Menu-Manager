package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class LoginTesterFile {

    public LoginTest test;

    @BeforeEach
    public void setup() throws FileNotFoundException {
        test = new LoginTest();
    }

    public void mockInputs() {
        test.username = new TextField("admin");
        test.password = new PasswordField();
        test.passwordText = new TextField();
//        test.showPassword = new ImageView();
        test.wrongLogin = new Label();
        test.password.setText("1234");
    }

    @Start
    public void start(Stage primaryStage) throws IOException {
        Stage stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.resizableProperty();
        stage.setTitle("Menu Manager");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testingTestFunction() throws IOException, ParseException {
        mockInputs();

        test.test();
        Assertions.assertEquals("Success!", test.wrongLogin.getText());
        Assertions.assertTrue(test.checkAdmin(test.username.getText(), test.password.getText()));

        test.username.setText("wrongAdmin");

        test.test();
        Assertions.assertEquals("Wrong username or password!", test.wrongLogin.getText());
        Assertions.assertFalse(test.checkAdmin(test.username.getText(), test.password.getText()));

        test.username.setText("admin");
        test.password.setText("wrongPassword");

        Assertions.assertFalse(test.checkAdmin(test.username.getText(), test.password.getText()));

        test.username.clear();
        test.password.clear();
        test.test();
        Assertions.assertEquals("Please enter your username and password", test.wrongLogin.getText());
    }

    @Test
    void testGoToOrder() throws IOException {
        test.goToOrder();
    }

    @Test
    void testUserLogin() throws IOException, ParseException {
        mockInputs();
        test.userLogin();
    }
}

package com.example.demo;


import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

public class LoginTesterFile {

    public LoginTest test;

    @BeforeEach
    public void setup() {
        test = new LoginTest();
    }

    @Test
    void testingTestFunction() throws IOException, ParseException {
        test.username = new TextField("admin");
        test.password = new PasswordField();
        test.passwordText = new TextField();
        test.wrongLogin = new Label();
        test.password.setText("1234");

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

//    @Test
//    void testGoToOrder() throws IOException {
//        test.goToOrder();
//    }

    @Test
    void testUserLogin() throws IOException, ParseException {
        test.username = new TextField("admin");
        test.password = new PasswordField();
        test.passwordText = new TextField();
        test.wrongLogin = new Label();
        test.password.setText("1234");

        test.userLogin();
    }
}


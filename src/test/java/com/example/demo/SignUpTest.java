package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;

@ExtendWith(ApplicationExtension.class)
public class SignUpTest {

    public Stage stage;
    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(HelloApplication.class);
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(HelloApplication.class.newInstance());
    }
    @Start
    public void start(Stage primaryStage) throws IOException {
        Stage stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.resizableProperty();
        stage.setTitle("Menu Manager");
        stage.setScene(scene);
        stage.show();
    }
}
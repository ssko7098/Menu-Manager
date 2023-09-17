package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.util.Locale.lookup;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class HistoryTest {

    public Stage stage;
//
    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(HelloApplication.class);
        FxToolkit.showStage();
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(HelloApplication.class.newInstance());
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
    void searchHistoryLogoutTest(FxRobot robot) throws TimeoutException, InterruptedException {

        //Seting up the userLogin TextField to be able to get the robot to access it
        TextField userLogin = robot.lookup("#username").queryAs(TextField.class);

        //Instructions specifying the login details of the Admin User for Testing
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("1234");
        robot.clickOn("#button");


        robot.clickOn("#orderHistoryButton");

        robot.clickOn("#orderNumber");
        robot.write("2");

        robot.clickOn("#menuOHButton");
        robot.clickOn("#orderHistoryButtonMenu");
        robot.clickOn("#ahOHButton");
        robot.clickOn("#orderHistoryButton");
        robot.clickOn("#logOutOHButton");

        userLogin = robot.lookup("#username").queryAs(TextField.class);

        //Setting the newStage to the Stage associated w/ the hello-view.fxml
        Stage newStage = (Stage)userLogin.getScene().getWindow();

        //Test that the Stages are indeed different
        Assertions.assertNotEquals(stage, newStage);

    }

}

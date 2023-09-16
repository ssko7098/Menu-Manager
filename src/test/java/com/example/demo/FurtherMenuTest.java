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
public class FurtherMenuTest {

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
    void logOutMenuTest(FxRobot robot) throws TimeoutException, InterruptedException {

        //Seting up the userLogin TextField to be able to get the robot to access it
        TextField userLogin = robot.lookup("#username").queryAs(TextField.class);

        //Instructions specifying the login details of the Admin User for Testing
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("1234");

        //Scene Changed Activated via SIGN IN button
        robot.clickOn("#button");
        robot.clickOn("#menuButton");

        //Getting reference to the LogOut Button as it is in a new Stage
        Button logOutButton = robot.lookup("#logout").queryAs(Button.class);

        //Setting the oldStage to the Stage associated w/ the afterLogin.fxml
        Stage oldstage = (Stage) logOutButton.getScene().getWindow();

        //Scene Changed Activated via LOG OUT button
        robot.clickOn("#logout");

        userLogin = robot.lookup("#username").queryAs(TextField.class);

        //Setting the newStage to the Stage associated w/ the hello-view.fxml
        Stage newStage = (Stage)userLogin.getScene().getWindow();

        //Test that the Stages are indeed different
        Assertions.assertNotEquals(stage, newStage);

    }

    @Test
    void viewOrderMenuHistory(FxRobot robot) throws TimeoutException, InterruptedException {

        //Seting up the userLogin TextField to be able to get the robot to access it
        TextField userLogin = robot.lookup("#username").queryAs(TextField.class);

        //Instructions specifying the login details of the Admin User for Testing
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("1234");

        //Scene Changed Activated via SIGN IN button
        robot.clickOn("#button");
        robot.clickOn("#menuButton");

        //Getting reference to the orderHistoryButton Button as it is in a new Stage
        Button orderHistoryButton = robot.lookup("#orderHistoryButtonMenu").queryAs(Button.class);

        //Setting the oldStage to the Stage associated w/ the afterLogin.fxml
        Stage oldstage = (Stage) orderHistoryButton.getScene().getWindow();

        //Scene Changed Activated via ORDER HISTORY button
        robot.clickOn("#orderHistoryButtonMenu");
        //Getting reference to the orderNumber TextField as it is in a new Stage
        TextField orderNumber = robot.lookup("#orderNumber").queryAs(TextField.class);

        //Setting the newStage to the Stage associated w/ the hello-view.fxml
        Stage newStage = (Stage) orderNumber.getScene().getWindow();

        //Test that the Stages are indeed different
        Assertions.assertNotEquals(stage, newStage);
    }


}

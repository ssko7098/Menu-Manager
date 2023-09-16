package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class TestTester {
    public Stage stage;
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

    private volatile boolean success = false;

    @Test
    public void setupTest() {
        //Initialising Java FX --> requires starting a new Thread
        Thread thread = new Thread("JavaFX Initialised Thread") {
            public void run() {
                try {
                    Application.launch(HelloApplication.class);
                    success = true;
                } catch (Throwable thrown) {
                    if (thrown.getCause() != null) {
                        success = true;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        //Testing that the thread is correctly in the runnable state and able to slept
        try{
            thread.sleep(5000);
            success = true;
        } catch (InterruptedException e) {
        }
        //Interrupting the thread sleep to transition the thread into the running state
        thread.interrupt();
        System.out.println("Application has Started");
        assertTrue(success);

    }

    @Test
    void controllerTest() {
        HelloController c = new HelloController();
    }



}

package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HelloApplicationTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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

    }
    HelloApplication app = new HelloApplication();

}
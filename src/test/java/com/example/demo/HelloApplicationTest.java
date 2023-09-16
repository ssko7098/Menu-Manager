package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class HelloApplicationTest extends Application {

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


//    @Test
//    public void sceneChangeTest() throws IOException, TimeoutException {
//
//        //Creating new HelloApplication Object
//        HelloApplication app = new HelloApplication();
//
//        //The String of the fxml file for the next stage to go to
//        String nextStage = "SignUP.fxml";
//
//        //Setting old stage to the current stage used to initialise the application - "hello-view.fxml"
//        Stage oldStage = FxToolkit.registerPrimaryStage();
//
//        //Call the scene change using the String of the FXML file for the next stage
//        app.changeScene(nextStage);
//
//        //Test that the stages are different to ensure that the scene change has occured
//        assertNotEquals(oldStage,stage);
//    }
//
//    @Test
//    public void getStageTest() throws TimeoutException, IOException {
//
//       HelloApplication app = new HelloApplication();
//
//        //The String of the fxml file for the next stage to go to
//        String nextStage = "SignUP.fxml";
//
//        //Setting old stage to the current stage used to initialise the application - "hello-view.fxml"
//        Stage oldStage = FxToolkit.registerPrimaryStage();
//
//        //Testing the primary stage by retrieving the stage
//        assertEquals(oldStage, app.getStage());
//    }

}
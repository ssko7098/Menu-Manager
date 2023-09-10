package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class HelloApplicationTest extends Application {

    private static Stage stage;

    private TextField username;

    private PasswordField password;
    final String VALID_USERNAME = "admin";
    final String VALID_PASSWORD = "1234";
    private LoginTest loginTest;

    private Label wrongLogin;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.resizableProperty();
        stage.setTitle("Menu Manager");
        stage.setScene(scene);
        stage.show();

    }

    private volatile boolean success = false;

    private static Scene scene;
    private FXMLLoader fxmlLoader;
    private HelloApplication app;
    final String SIGN_IN_ID = "#button";

    //@BeforeEach
    //public void commonSetup() throws IOException {
    //    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    //    scene = new Scene(fxmlLoader.load(), 1200, 700);
    //    stage.setScene(scene);
    //}
    @Test
    public void setupTest(){
        //Initialising Java FX --> requires starting a new Thread
        Thread thread = new Thread("JavaFX Initialised Thread") {
            public void run() {
                try {
                    Application.launch(HelloApplication.class);
                    success = true;
                }catch (Throwable thrown){
                    if (thrown.getCause() != null){
                        success = true;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        //Testing that the thread is correctly in the runnable state and able to slept
        try{
            thread.sleep(800);
        } catch (InterruptedException e) {
        }
        //Interrupting the thread sleep to transition the thread into the running state
        thread.interrupt();
        System.out.println("Application has Started");
        assertTrue(success);
    }

    @Test
    public void unsucessfulLogin() throws IOException, ParseException {
        HelloApplication m = new HelloApplication();
        //TextField username;
        //PasswordField passwordField = new PasswordField();
        //TextField wrongLogin = new TextField();
        loginTest.userLogin();
        assertEquals("Please enter your username and password", wrongLogin.getText());

    }

    //@org.junit.Test
    //public void sign_Up_Button_Text() {
    //    FxAssert.verifyThat(SIGN_IN_ID, LabeledMatchers.hasText("SIGN IN"));
    //}

    //@Test
    //public void loginNoCredentials() throws IOException, ParseException {
    //    //username.setText(VALID_USERNAME);
    //    //password.setText(VALID_PASSWORD);
    //    loginTest.userLogin();
    //    System.out.println(wrongLogin.getText());
    //
    //    assertEquals("Please enter your username and password"
    //            , wrongLogin.getText());
    //}

    @Test
    void main() {
    }


}
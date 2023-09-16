package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class Completion {



    public void back() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

}

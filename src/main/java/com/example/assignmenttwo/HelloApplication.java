package com.example.assignmenttwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        String style = getClass().getResource("/style.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 872, 480);
        scene.getStylesheets().add(style);
        stage.setTitle("MEDIA");
        stage.setScene(scene);
        stage.show();

        Slider volume = new Slider();
    }

    public static void main(String[] args) {
        launch();
    }
}
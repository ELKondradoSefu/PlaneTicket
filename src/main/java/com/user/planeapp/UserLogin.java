package com.user.planeapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class UserLogin extends Application {

    public static final String CURRENCY="$";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Interface.fxml")));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Plane Market");
        primaryStage.setScene(new Scene(root,1350,750));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
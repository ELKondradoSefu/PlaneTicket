package com.user.planeapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserLogin extends Application {
    @Override
            public void start(Stage primaryStage) throws Exception{
             Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
             primaryStage.initStyle(StageStyle.UNDECORATED);

             //primaryStage.setTitle("Application");
             primaryStage.setScene(new Scene(root, 818, 484));
             primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

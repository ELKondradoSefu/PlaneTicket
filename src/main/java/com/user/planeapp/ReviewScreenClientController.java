package com.user.planeapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewScreenClientController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private AnchorPane reviewHome;
    @FXML
    private Button homePageButton;


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void getPage(String filename) throws IOException {
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource(filename + ".fxml"));
        reviewHome.getChildren().setAll(node);

    }

    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {

        getPage("Interface");
    }
}

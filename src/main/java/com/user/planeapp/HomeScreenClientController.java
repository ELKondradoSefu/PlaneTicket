package com.user.planeapp;

import com.user.planeapp.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeScreenClientController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private Button closeButton;
    @FXML
    private Button homePageButton;
    @FXML
    private AnchorPane home;
    @FXML
    private Button shopButton;
    @FXML
    private Button historyButton;



    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {

        getPage("Interface");
    }

    public void shopButtonOnAction(ActionEvent event) throws IOException{
    try {
        Parent rootShop = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FlightsScreenClient.fxml")));
        Stage viewFlightsStage = new Stage();
        viewFlightsStage.setTitle("Flights Market");
        viewFlightsStage.setScene(new Scene(rootShop, 1350, 750));
        viewFlightsStage.show();
    }catch(NullPointerException ignored)
    {}
    }

    public void historyButtonOnAction(ActionEvent event) throws IOException{
        getPage("ReviewScreenClient");

    }

    public void getPage(String filename) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource(filename + ".fxml"));
        home.getChildren().setAll(node);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userLabel.setText(User.name);

    }
}

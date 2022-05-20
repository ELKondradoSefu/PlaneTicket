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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeScreenAdminController implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button homePageButton;
    @FXML
    private AnchorPane homeAdmin;
    @FXML
    private Button adminAdd;
    @FXML
    private Button adminReview;



    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {

        getPage("Interface");
    }

    public void addFlight(ActionEvent event) throws IOException{
    try {
        Parent rootShop = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FlightsScreenClient.fxml")));
        Stage viewFlightsStage = new Stage();
        viewFlightsStage.setTitle("Flights Market");
        viewFlightsStage.setScene(new Scene(rootShop, 1350, 750));
        viewFlightsStage.show();
    }catch(NullPointerException ignored)
    {}
    }

    public void reviewSales (ActionEvent event) throws IOException{
        getPage("ReviewScreenAdmin");

    }

    public void getPage(String filename) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource(filename + ".fxml"));
        homeAdmin.getChildren().setAll(node);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

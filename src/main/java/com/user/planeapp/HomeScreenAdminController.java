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
    private Button adminViewFlights;
    @FXML
    private Button adminReview;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Interface.fxml")));
        Stage homeStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homeStage.setTitle("Interface");
        homeStage.setScene(new Scene(root,1350,750));
        homeStage.show();
    }

    public void viewFlights(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FlightsScreenAdmin.fxml")));
        Stage addFlightStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addFlightStage.setTitle("Add new flight");
        addFlightStage.setScene(new Scene(root,1350,750));
        addFlightStage.show();
    }

    public void reviewSales(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ReviewScreenAdmin.fxml")));
        Stage reviewSalesStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        reviewSalesStage.setTitle("Interface");
        reviewSalesStage.setScene(new Scene(root,1350,750));
        reviewSalesStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

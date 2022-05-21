package com.user.planeapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewScreenClientController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private AnchorPane reviewHome;
    @FXML
    private Button homePageButton;
    @FXML
    private AreaChart<String,Double> areaChart;


    public void backButtonOnAction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenClient.fxml")));
        Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homePageStage.setTitle("Home Client");
        homePageStage.setScene(new Scene(homePageParent,1350,750));
        homePageStage.show();
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Interface.fxml")));
        Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homePageStage.setTitle("Interface");
        homePageStage.setScene(new Scene(homePageParent,1350,750));
        homePageStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewFlightsController viewFlightsController = new ViewFlightsController();
        List<Flight> flights = viewFlightsController.getData();
        XYChart.Series<String,Double> series = new XYChart.Series<>();

        for(int i=0;i<flights.size();i++)
        {
            series.getData().add(new XYChart.Data<String, Double>(flights.get(i).getName(),10*flights.get(i).getPrice()/flights.get(i).getFlightDuration()));
        }
        areaChart.getData().add(series);
    }


}

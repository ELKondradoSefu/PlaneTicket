package com.user.planeapp;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewScreenAdminController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ListView<String>salesView;
    @FXML
    private Label mySales;
    @FXML
    private NumberAxis Visits;
    @FXML
    private CategoryAxis Destinations;
    @FXML
    private BarChart<String, Number> Chart;

    String[]flights = {"Tokyo", " Istanbul","London", "New York", "Ottawa", "Kiev", "Beijing", "Vienna","Buenos Aires"};
    String currenFlight;
    List<Flight> flightsList = new ArrayList<>();
    ViewFlightsController viewFlightsController = new ViewFlightsController();

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void backButtonOnAction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenAdmin.fxml")));
        Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homePageStage.setTitle("Home Admin");
        homePageStage.setScene(new Scene(homePageParent,1350,750));
        homePageStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        salesView.getItems().addAll(flights);
        salesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currenFlight = salesView.getSelectionModel().getSelectedItem();
                mySales.setText(currenFlight);
            }
        });

        flightsList = viewFlightsController.getData();

        XYChart.Series<String,Number>series = new XYChart.Series<>();
        series.setName("Flight Comparison");

        for(int i=0;i<flightsList.size();i++)
        {
            series.getData().add(new XYChart.Data<>(flightsList.get(i).getName(),flightsList.get(i).getTicketSum()));
        }

        Chart.getData().add(series);
    }
}

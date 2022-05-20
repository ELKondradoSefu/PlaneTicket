package com.user.planeapp;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
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
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
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

        XYChart.Series<String,Number>series = new XYChart.Series<>();
        series.setName("Flight Comparison");
        series.getData().add(new XYChart.Data<>("Tokyo",12));
        series.getData().add(new XYChart.Data<>("Istanbul",12));
        series.getData().add(new XYChart.Data<>("London",30));
        series.getData().add(new XYChart.Data<>("New York",25));
        series.getData().add(new XYChart.Data<>("Ottawa",12));
        series.getData().add(new XYChart.Data<>("Kiev",20));

        Chart.getData().add(series);

    }
}

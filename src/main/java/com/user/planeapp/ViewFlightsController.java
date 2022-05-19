package com.user.planeapp;

import com.user.planeapp.Flight;
import com.user.planeapp.services.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewFlightsController implements Initializable {
    @FXML
    private VBox chosenFlight;

    @FXML
    private ImageView flightImg;

    @FXML
    private Label flightNameLabel;

    @FXML
    private Label flightPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Flight> flights = new ArrayList<Flight>();
    private Image image;

    private List<Flight> getData()
    {
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        flight = new Flight();
        flight.setName("Istambul");
        flight.setPrice(45.99);
        flight.setImgSrc("/com/user/planeapp/images/istambul.jpg");
        flight.setColor("6A7324");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Rome");
        flight.setPrice(53.99);
        flight.setImgSrc("/com/user/planeapp/images/rome.jpg");
        flight.setColor("6A7324");
        flights.add(flight);

        flight = new Flight();
        flight.setName("London");
        flight.setPrice(22.99);
        flight.setImgSrc("/com/user/planeapp/images/london.jpg");
        flight.setColor("6A7324");
        flights.add(flight);

        return flights;
    }

    private void setChosenFlight(Flight flight)
    {
        try {
            flightNameLabel.setText(flight.getName());
            flightPriceLabel.setText(UserLogin.CURRENCY + flight.getPrice());
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(flight.getImgSrc())));
            flightImg.setImage(image);
            chosenFlight.setStyle("-fx-background-color: #" + flight.getColor() + ";\n" + "-fx-background-radius: 30");
        }catch (NullPointerException e){}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flights.addAll(getData());
        if(flights.size()>0) {
            setChosenFlight(flights.get(0));
        }
        int column = 0;
        int row = 1;
        try
        {
            for(int i=0;i<flights.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(flights.get(i));

                if(column == 3){
                    column=0;
                    row++;
                }
                grid.add(anchorPane,column++,row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane,new Insets(10));

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


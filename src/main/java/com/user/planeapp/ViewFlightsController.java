package com.user.planeapp;

import com.user.planeapp.Interfaces.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
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

    @FXML
    private ComboBox<Integer> comboBox;

    private List<Flight> flights = new ArrayList<Flight>();
    private Image image;
    private MyListener myListener;

    private List<Flight> getData()
    {
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        flight = new Flight();
        flight.setName("Istanbul");
        flight.setPrice(45);
        flight.setImgSrc("/com/user/planeapp/images/istambul.jpg");
        flight.setColor("6A7324");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Rome");
        flight.setPrice(53);
        flight.setImgSrc("/com/user/planeapp/images/rome.jpg");
        flight.setColor("8A2BE2");
        flights.add(flight);

        flight = new Flight();
        flight.setName("London");
        flight.setPrice(22);
        flight.setImgSrc("/com/user/planeapp/images/london.jpg");
        flight.setColor("CD853F");
        flights.add(flight);


        flight = new Flight();
        flight.setName("New York");
        flight.setPrice(84);
        flight.setImgSrc("/com/user/planeapp/images/newyork.jpg");
        flight.setColor("DC143C");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Ottawa");
        flight.setPrice(110);
        flight.setImgSrc("/com/user/planeapp/images/ottawa.jpg");
        flight.setColor("8B008B");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Kiev");
        flight.setPrice(10);
        flight.setImgSrc("/com/user/planeapp/images/kiev.jpg");
        flight.setColor("8FBC8F");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Tokyo");
        flight.setPrice(49);
        flight.setImgSrc("/com/user/planeapp/images/tokyo.jpg");
        flight.setColor("228B22");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Beijing");
        flight.setPrice(33);
        flight.setImgSrc("/com/user/planeapp/images/beijing.jpg");
        flight.setColor("FF69B4");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Vienna");
        flight.setPrice(15);
        flight.setImgSrc("/com/user/planeapp/images/viena.jpg");
        flight.setColor("F08080");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Buenos Aires");
        flight.setPrice(70);
        flight.setImgSrc("/com/user/planeapp/images/buenosaires.jpg");
        flight.setColor("9370DB");
        flights.add(flight);

        return flights;
    }

    public void setChosenFlight(Flight flight)
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
            myListener = new MyListener() {
                @Override
                public void onClickListener(Flight flight) {
                    setChosenFlight(flight);
                }
            };
            comboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
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
                itemController.setData(flights.get(i),myListener);

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


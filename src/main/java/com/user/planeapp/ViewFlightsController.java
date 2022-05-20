package com.user.planeapp;

import com.user.planeapp.Interfaces.MyListener;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
    private Label flightDateLabel;

    @FXML
    private Label flightDurationLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ComboBox comboBox = new ComboBox();

    @FXML
    private TextField comboBoxTextField;

    @FXML
    private Button closeLogin;

    @FXML
    private TextField myCart;

    private List<Flight> flights = new ArrayList<Flight>();
    private Image image;
    private MyListener myListener;
    private double myTicketsSum=0;

    private List<Flight> getData()
    {
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        flight = new Flight();
        flight.setName("Istambul");
        flight.setPrice(45.4);
        flight.setImgSrc("/com/user/planeapp/images/istambul.jpg");
        flight.setColor("6A7324");
        flight.setFlightDuration(128);
        flight.setDate("11/05/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Rome");
        flight.setPrice(53);
        flight.setImgSrc("/com/user/planeapp/images/rome.jpg");
        flight.setColor("8A2BE2");
        flight.setFlightDuration(110);
        flight.setDate("5/05/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("London");
        flight.setPrice(22.35);
        flight.setImgSrc("/com/user/planeapp/images/london.jpg");
        flight.setColor("CD853F");
        flight.setFlightDuration(127);
        flight.setDate("30/11/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("New York");
        flight.setPrice(99.99);
        flight.setImgSrc("/com/user/planeapp/images/newyork.jpg");
        flight.setColor("DC143C");
        flight.setFlightDuration(155);
        flight.setDate("4/2/2023");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Ottawa");
        flight.setPrice(143.99);
        flight.setImgSrc("/com/user/planeapp/images/ottawa.jpg");
        flight.setColor("8B008B");
        flight.setFlightDuration(330);
        flight.setDate("3/10/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Kiev");
        flight.setPrice(10);
        flight.setImgSrc("/com/user/planeapp/images/kiev.jpg");
        flight.setColor("8FBC8F");
        flight.setFlightDuration(60);
        flight.setDate("14/11/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Tokyo");
        flight.setPrice(49.99);
        flight.setImgSrc("/com/user/planeapp/images/tokyo.jpg");
        flight.setColor("228B22");
        flight.setFlightDuration(160);
        flight.setDate("28/7/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Beijing");
        flight.setPrice(33);
        flight.setImgSrc("/com/user/planeapp/images/beijing.jpg");
        flight.setColor("FF69B4");
        flight.setFlightDuration(125);
        flight.setDate("22/9/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Viena");
        flight.setPrice(15.99);
        flight.setImgSrc("/com/user/planeapp/images/viena.jpg");
        flight.setColor("F08080");
        flight.setFlightDuration(55);
        flight.setDate("10/10/2022");
        flights.add(flight);

        flight = new Flight();
        flight.setName("Buenos Aiers");
        flight.setPrice(72.5);
        flight.setImgSrc("/com/user/planeapp/images/buenosaires.jpg");
        flight.setColor("9370DB");
        flight.setFlightDuration(280);
        flight.setDate("16/12/2022");
        flights.add(flight);

        return flights;
    }

    public void backButtonOnAction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenClient.fxml")));
        Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homePageStage.setTitle("Home Admin");
        homePageStage.setScene(new Scene(homePageParent,1350,750));
        homePageStage.show();
    }

    public void closeLoginOnAction(ActionEvent event){
        Stage stage = (Stage)closeLogin.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void buyButton(ActionEvent actionEvent)
    {
        String aux = flightPriceLabel.getText();
        aux = aux.substring(1);
        myTicketsSum += (comboBox.getSelectionModel().getSelectedIndex()+1) * Double.parseDouble(aux);
        myCart.setText(UserLogin.CURRENCY + String.valueOf(myTicketsSum));
    }


    public void setChosenFlight(Flight flight)
    {
        flightNameLabel.setText(flight.getName());
        flightPriceLabel.setText(UserLogin.CURRENCY + flight.getPrice());
        flightDateLabel.setText(flight.getDate());
        flightDurationLabel.setText(String.valueOf(flight.getFlightDuration()));
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(flight.getImgSrc())));
        flightImg.setImage(image);
        chosenFlight.setStyle("-fx-background-color: #" + flight.getColor() + ";\n" + "-fx-background-radius: 30");
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
            comboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
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


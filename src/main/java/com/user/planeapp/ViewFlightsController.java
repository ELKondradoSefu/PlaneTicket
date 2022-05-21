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
import javafx.scene.chart.XYChart;
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
    private ComboBox<Integer> comboBox = new ComboBox<>();
    @FXML
    private TextField comboBoxTextField;
    @FXML
    private Button closeLogin;
    @FXML
    private TextField myCart;
    @FXML
    private TextField searchBoxFlight;
    @FXML
    private Label errorOnSearch;

    private List<Flight> flights = new ArrayList<Flight>();
    private Image image;
    private MyListener myListener;
    private double myTicketsSum=0;

    public Flight addFlight(String name,double price,String imdSrc,String color,int flightDuration,String date)
    {
        Flight flight = new Flight();
        flight.setName(name);
        flight.setPrice(price);
        flight.setImgSrc("/com/user/planeapp/images/" + imdSrc);
        flight.setColor(color);
        flight.setFlightDuration(flightDuration);
        flight.setDate(date);
        return flight;
    }

    public List<Flight> getData()
    {
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        flight = addFlight("Istambul",45.4,"istambul.jpg","6A7324",128,"11/05/2022");
        flights.add(flight);

        flight = addFlight("Rome",53,"rome.jpg","8A2BE2",110,"5/05/2022");
        flights.add(flight);

        flight = addFlight("London",22.35,"london.jpg","CD853F",127,"30/11/2022");
        flights.add(flight);

        flight = addFlight("New York",99.99,"newyork.jpg","DC143C",155,"4/2/2023");
        flights.add(flight);

        flight = addFlight("Ottawa",143.99,"ottawa.jpg","8B008B",330,"3/10/2022");
        flights.add(flight);

        flight = addFlight("Kiev",10,"kiev.jpg","8FBC8F",60,"14/11/2022");
        flights.add(flight);

        flight = addFlight("Tokyo",49.99,"tokyo.jpg","228B22",160,"28/7/2022");
        flights.add(flight);

        flight = addFlight("Beijing",33,"beijing.jpg","FF69B4",125,"22/9/2022");
        flights.add(flight);

        flight = addFlight("Vienna",15.99,"viena.jpg","F08080",55,"10/10/2022");
        flights.add(flight);

        flight = addFlight("Buenos Aiers",72.5,"buenosaires.jpg","9370DB",280,"16/12/2022");
        flights.add(flight);

        flight = addFlight("Paris",95,"paris.jpg","9ACD32",133,"10/11/2023");
        flights.add(flight);

        flight = addFlight("Seoul",269.99,"seoul.jpg","ff1e90",250,"01/05/2022");
        flights.add(flight);

        flight = addFlight("Budapest",15,"budapesta.jpg","2F4F4F",22,"29/11/2022");
        flights.add(flight);

        flight = addFlight("Lima",199.99,"Lima.jpg","A52A2A",225,"10/11/2023");
        flights.add(flight);

        flight = addFlight("Bucharest",19.99,"bucuresti.jpg","008080",35,"30/01/2023");
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

    public void searchForFlight(ActionEvent actionEvent)
    {
        errorOnSearch.setText("");
        String searchBox = searchBoxFlight.getText();
        for(int i=0;i<flights.size();i++)
            if(searchBox.equals(flights.get(i).getName())) {
                setChosenFlight(flights.get(i));
                return;
            }
        errorOnSearch.setText("We dont have that flight");
    }

    public void buyButton(ActionEvent actionEvent)
    {
        String aux = flightPriceLabel.getText();
        aux = aux.substring(1);
        myTicketsSum += (comboBox.getSelectionModel().getSelectedIndex()+1) * Double.parseDouble(aux);
        myCart.setText(UserLogin.CURRENCY + String.valueOf(myTicketsSum));

        for(int i=0;i<flights.size();i++)
            if(flights.get(i).getName().equals(flightNameLabel.getText())) {
                flights.get(i).setTicketSum((comboBox.getSelectionModel().getSelectedIndex()+1) * Double.parseDouble(aux));
                return;
            }
    }

    public void setChosenFlight(Flight flight)
    {
        flightNameLabel.setText(flight.getName());
        flightPriceLabel.setText(UserLogin.CURRENCY + flight.getPrice());
        flightDateLabel.setText(flight.getDate());
        flightDurationLabel.setText(String.valueOf(flight.getFlightDuration()));
        comboBox.getSelectionModel().clearSelection();
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


package com.user.planeapp;

import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

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
    @FXML
    private TextArea textAreaUsers;

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

    public void usersList() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/usersClient.json");
        Scanner scan = new Scanner(fileReader);

        while(scan.hasNext())
        {
            ArrayList<String> firstName = new ArrayList<>();
            ArrayList<String> email = new ArrayList<>();
            ArrayList<String> lastName = new ArrayList<>();

            String usersList = scan.nextLine();
            StringTokenizer st = new StringTokenizer(usersList,":[{}];,");
            int userCount = 0;
            int count = 0;
            while(st.hasMoreTokens())
            {
                String aux = st.nextToken();
                count++;
                if(count%3==0)
                {
                    userCount++;
                    if(userCount==1)
                        firstName.add(aux);
                    else if(userCount==2) {
                    }
                    else if(userCount==3)
                        email.add(aux);
                    else
                    {
                        lastName.add(aux);
                        userCount=0;
                    }
                }
            }
            for(int i=0;i<firstName.size();i++) {
                firstName.set(i,firstName.get(i).substring(1,firstName.get(i).length()-1));
                email.set(i,email.get(i).substring(1,email.get(i).length()-1));
                lastName.set(i,lastName.get(i).substring(1,lastName.get(i).length()-1));
                textAreaUsers.appendText(i+1 + ".First Name:" + firstName.get(i) + "\nEmail:" + email.get(i) + "\nLast Name:" + lastName.get(i) + "\n\n");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textAreaUsers.setText("Users:\n");
        try {
            usersList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

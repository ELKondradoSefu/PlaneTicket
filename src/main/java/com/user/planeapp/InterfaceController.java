package com.user.planeapp;

import com.user.planeapp.exceptions.IncorrectMailOrPassword;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button createButton;
    @FXML
    private Button closeLogin;

    public static String email;

    public InterfaceController() {
    }

    public void createAccount(ActionEvent event) throws IOException{
        createAccountForm(event);
    }

    public void closeLoginOnAction(ActionEvent event){
        Stage stage = (Stage)closeLogin.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void handleLoginButtonAction(ActionEvent event) throws IOException {
        try{
            //verify if the user is client
            if((User.loginCheckClient(username.getText(),password.getText()).equals("Client")))
            {
                //switch to home screen client
                Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenClient.fxml")));
                Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                homePageStage.setTitle("Home Admin");
                homePageStage.setScene(new Scene(homePageParent,1350,750));
                homePageStage.show();

                email=username.getText();
                //verify if the user is the admin
            } else if(username.getText().equals("admin@planeapp.com") && password.getText().equals("adminHome"))
            {
                //switch to home screen admin
                Parent homeAdminParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenAdmin.fxml")));
                Stage homeAdminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                homeAdminStage.setTitle("Home Admin");
                homeAdminStage.setScene(new Scene(homeAdminParent,1350,750));
                homeAdminStage.show();
            }else
            {
                //if the user is none of the above throw exception
                User.checkIncorrect();
            }
            } catch (IncorrectMailOrPassword  e) {
                  //error if not all fields are completed
                  wrongLogIn.setText(e.getMessage());
        }
    }

    //display register stage
    public void createAccountForm(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            registerStage.setTitle("Register");
            registerStage.setScene(new Scene(root, 1350, 750));
            registerStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
package com.user.planeapp;

import com.user.planeapp.exceptions.EmptyFieldException;
import com.user.planeapp.exceptions.UsernameNotAvailable;
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


public class RegisterController implements Initializable {
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextfield;
    @FXML
    private TextField lastnameTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private Button returnSignin;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException{
        try
        {
            User.addUserClient(firstnameTextfield.getText(),lastnameTextfield.getText(),emailTextfield.getText(),setPasswordField.getText());
            //Load the Home Page for client
            homepageClient(event);
        }
        catch(EmptyFieldException | UsernameNotAvailable e){
            //error if not all fields are completed
            registrationMessageLabel.setText(e.getMessage());
        }
    }

    //return to sign in form if you already have an account
    public void returnSigninOnAction(ActionEvent event) throws IOException{
        signinForm(event);
    }

    public void signinForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Interface.fxml")));
        Stage signInStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        signInStage.setTitle("Interface");
        signInStage.setScene(new Scene(root, 1350, 750));
        signInStage.show();;
    }

    public void homepageClient(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreenClient.fxml")));
        Stage homePageStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        homePageStage.setTitle("Home Admin");
        homePageStage.setScene(new Scene(homePageParent,1350,750));
        homePageStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

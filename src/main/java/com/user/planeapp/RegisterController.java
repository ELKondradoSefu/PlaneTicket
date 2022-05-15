package com.user.planeapp;

import com.user.planeapp.exceptions.EmptyFieldException;
import com.user.planeapp.exceptions.UsernameNotAvailable;
import com.user.planeapp.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    //close button
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException{

        try{
            User.addUserClient(firstnameTextfield.getText(),lastnameTextfield.getText(),emailTextfield.getText(),setPasswordField.getText());
            //Load the Home Page for client
            homepageClient();


        }
        catch(EmptyFieldException | UsernameNotAvailable e){
            //error if not all fields are completed
            registrationMessageLabel.setText(e.getMessage());
        }

    }

    //return to sign in form if you already have an account
    public void returnSigninOnAction(ActionEvent event) throws IOException{
        signinForm();

    }

    public void signinForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 818, 484));
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    //home page opener
    public void homepageClient(){
        try{
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeScreenClient.fxml"));
            Stage home_page_scene = new Stage();
            home_page_scene.initStyle(StageStyle.UNDECORATED);
            home_page_scene.setScene(new Scene(home_page_parent, 818, 484));
            home_page_scene.show();

        }catch(IOException e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

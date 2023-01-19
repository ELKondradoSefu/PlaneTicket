package com.user.controllers;

import com.user.planeapp.RegisterController;
import com.user.planeapp.services.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SignUpClientControllerTest extends ApplicationTest {
    public static final String TEST_FIRSTNAME = "testFirstName";
    public static final String TEST_LASTNAME = "testLastName";
    public static final String TEST_EMAIL = "testEmail";
    public static final String TEST_PASSWORD = "testPassword";
    public static final ActionEvent event = new ActionEvent();

    private RegisterController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RegisterController();
        controller.firstnameTextfield = new TextField();
        controller.lastnameTextfield = new TextField();
        controller.emailTextfield = new TextField();
        controller.setPasswordField = new PasswordField();
        controller.registrationMessageLabel = new Label();
        controller.firstnameTextfield.setText(TEST_FIRSTNAME);
        controller.lastnameTextfield.setText(TEST_LASTNAME);
        controller.emailTextfield.setText(TEST_EMAIL);
        controller.setPasswordField.setText(TEST_PASSWORD);
    }


    @AfterClass
    public static void afterClass() throws Exception {
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        arrayClient.clear();
        try {
            File file = new File("src/main/resources/usersClient.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public JSONArray getJsonClient() throws Exception {
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        FileReader readFile = new FileReader("src/main/resources/usersClient.json");
        BufferedReader read = new BufferedReader(readFile);
        p = jp.parse(read);
        if (p instanceof JSONArray) {
            arrayClient = (JSONArray) p;
        }
        return arrayClient;

    }

    @Test
    public void handleSignUpButtonAction() throws Exception {
        controller.firstnameTextfield.setText(TEST_FIRSTNAME);
        controller.registerButtonOnAction(event);
        assertEquals(1, getJsonClient().size());

    }

    @Test
    public void testEmailAlreadyExists() throws Exception {
        controller.firstnameTextfield.setText(TEST_FIRSTNAME);
        User.addUserClient(TEST_FIRSTNAME,TEST_LASTNAME,TEST_EMAIL,TEST_PASSWORD);
        controller.registerButtonOnAction(event);
        assertEquals("This email address is not available.",(String) controller.registrationMessageLabel.getText());

    }

    @Test
    public void testEmptyFields() throws Exception {
        controller.firstnameTextfield.clear();
        controller.registerButtonOnAction(event);
        assertEquals("All fields are required!", (String) controller.registrationMessageLabel.getText());

    }

}
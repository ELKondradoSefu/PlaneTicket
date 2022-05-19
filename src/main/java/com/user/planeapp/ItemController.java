package com.user.planeapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    private Flight flight;

    public void setData(Flight flight)
    {
        try {
            this.flight = flight;
            nameLabel.setText(flight.getName());
            priceLabel.setText(UserLogin.CURRENCY + flight.getPrice());
            Image image;
            image = new Image(getClass().getResourceAsStream(flight.getImgSrc()));
            img.setImage(image);
        }catch(NullPointerException ignored) {
        }
    }




}


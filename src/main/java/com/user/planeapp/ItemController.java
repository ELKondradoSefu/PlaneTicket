package com.user.planeapp;

import com.user.planeapp.Interfaces.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private Label duration;

    @FXML
    private Label date;

    private Flight flight;
    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(flight);
    }

    public void setData(Flight flight,MyListener myListener)
    {
            this.flight = flight;
            this.myListener=myListener;

            nameLabel.setText(flight.getName());
            priceLabel.setText(UserLogin.CURRENCY + flight.getPrice());
            duration.setText(String.valueOf(flight.getFlightDuration()));
            date.setText(flight.getDate());
            Image image;
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(flight.getImgSrc())));
            img.setImage(image);
    }
}


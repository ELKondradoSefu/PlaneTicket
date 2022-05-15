module com.user.planeapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;
    requires json.simple;


    opens com.user.planeapp to javafx.fxml;
    exports com.user.planeapp;
}
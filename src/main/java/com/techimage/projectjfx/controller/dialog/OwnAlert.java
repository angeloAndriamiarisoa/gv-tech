package com.techimage.projectjfx.controller.dialog;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;

public class OwnAlert {

    public static ButtonData optionalAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult().getButtonData();
    }
}

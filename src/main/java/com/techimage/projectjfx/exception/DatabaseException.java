package com.techimage.projectjfx.exception;

import com.techimage.projectjfx.controller.toast.Toast;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
    public DatabaseException(int errorCode) {
        System.out.println(errorCode);
        String content = "";
        switch (errorCode) {
            case 19 :
                content = "Duplicata";
                break;
            default:
                content = "SQLITE errror";
                break;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(content);
        alert.show();

    }
}

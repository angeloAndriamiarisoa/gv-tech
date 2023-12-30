package com.techimage.projectjfx.controller.dialog;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public class Dialog {
    public static Stage dialog;

    public static void showDialog(String title, AnchorPane content, Function function) {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        dialog.setResizable(false);
        Scene scene = new Scene(content);
        dialog.setScene(scene);
        dialog.show();

        dialog.setOnHidden(windowEvent -> {
            function.apply(null);
        });
    }
}

package com.techimage.projectjfx.controller.navigation;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NavigationUtil {
    public static void navigate(VBox parent, Label navItem, BorderPane destination) {

        navItem.setOnMouseClicked((event) -> {
            parent.getChildren().forEach(item -> {

                try {
                    AnchorPane anchorPane = (AnchorPane) item;

                    resetStyle((Label) anchorPane.getChildren().get(0));

                }
                catch (Exception exception) {}
            });
            if(!navItem.getStyleClass().contains("clicked")) {
                navItem.getStyleClass().add("clicked");
            }

            changeBody((BorderPane) parent.getScene().getRoot(), destination);

        });
    }
    private static void resetStyle (Label navItem) {
        if(navItem.getStyleClass().contains("clicked")) {
            navItem.getStyleClass().remove("clicked");
        }
    }

    public static void changeBody (BorderPane root, BorderPane newBody) {
        SplitPane splitPane = (SplitPane) root.getChildren().get(0);
        splitPane.getItems().remove(1);

        splitPane.getItems().add(newBody);

    }
}

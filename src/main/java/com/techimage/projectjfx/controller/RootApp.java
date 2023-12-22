package com.techimage.projectjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RootApp extends BorderPane {
    @FXML
    public AnchorPane navigationPart;

    @FXML
    public AnchorPane bodyPart;

    public AnchorPane getBodyPart() {
        return bodyPart;
    }
    public void setBodyPart(AnchorPane bodyPart) {
        this.bodyPart = bodyPart;
    }
    public AnchorPane getNavigationPart() {
        return navigationPart;
    }

    public void setNavigationPart(AnchorPane navigationPart) {
        this.navigationPart = navigationPart;
    }

}

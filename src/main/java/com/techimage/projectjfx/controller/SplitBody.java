package com.techimage.projectjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SplitBody {
    @FXML
    public AnchorPane navigationPart;

    @FXML
    public AnchorPane BodyPart;

    public AnchorPane getBodyPart() {
        return BodyPart;
    }
    public void setBodyPart(AnchorPane bodyPart) {
        BodyPart = bodyPart;
    }
    public AnchorPane getNavigationPart() {
        return navigationPart;
    }

    public void setNavigationPart(AnchorPane navigationPart) {
        this.navigationPart = navigationPart;
    }


}

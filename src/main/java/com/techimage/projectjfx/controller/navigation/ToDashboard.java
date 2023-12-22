package com.techimage.projectjfx.controller.navigation;

import javafx.scene.control.Label;

public class ToDashboard implements To{

    @Override
    public void replace(Label navItem) {
        if(!navItem.getStyleClass().contains("clicked")) {
            navItem.getStyleClass().add("clicked");
        }
    }
}

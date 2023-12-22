package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable  {

    @FXML
    public BorderPane borderPane;

    @FXML
    public Label title;

    @FXML
    public PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

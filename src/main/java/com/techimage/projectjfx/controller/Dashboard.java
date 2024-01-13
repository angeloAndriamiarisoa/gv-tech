package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable  {


    @FXML public AnchorPane anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LineChart lineChart = initLineChart();
        anchorPane.getChildren().add(lineChart);

    }

    private LineChart initLineChart() {
       // NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();

        // Créer le LineChart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Exemple de LineChart");

        // Créer les données pour la première série
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("mois1", 10));
        series1.getData().add(new XYChart.Data<>("mois2", 20));
        series1.getData().add(new XYChart.Data<>("mois31", 15));
        series1.setName("Série 1");

        // Créer les données pour la deuxième série
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>("mois1", 15));
        series2.getData().add(new XYChart.Data<>("mois2", 10));
        series2.getData().add(new XYChart.Data<>("mois3", 25));
        series2.setName("Série 2");

        // Ajouter les séries au LineChart
        lineChart.getData().addAll(series1, series2);

        // Personnaliser les couleurs des lignes
        String colorStyle1 = "-fx-stroke: #FF0000;"; // Rouge
        String colorStyle2 = "-fx-stroke: #00FF00;"; // Vert
        lineChart.lookup(".chart-series-line.series0").setStyle(colorStyle1);
        lineChart.lookup(".chart-series-line.series1").setStyle(colorStyle2);
        return lineChart;

    }
}

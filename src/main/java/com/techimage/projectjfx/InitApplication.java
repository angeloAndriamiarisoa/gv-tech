package com.techimage.projectjfx;

import com.techimage.projectjfx.util.ResourceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class InitApplication  {
    public static Stage primaryStage;
    private BorderPane rootContainer;
    public InitApplication  (Stage stage) {
        Application.setUserAgentStylesheet(
                MainApp.class.getResource("assets/css/primer-light.css")
                .toExternalForm());
        primaryStage = stage;

    }

    public void init () {
        Image icon = new Image(MainApp.class
                .getResource("assets/icon/techimage.jpg")
                .toExternalForm());

        primaryStage.setTitle("TechStock");
        primaryStage.getIcons().add(icon);
        this.initRootContainers();
    }

    private void initRootContainers () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("root-app.fxml"));
            String css = MainApp.class.getResource("assets/css/myCss.css").toString();

            this.rootContainer = loader.load();
            SplitPane splitPane = (SplitPane) this.rootContainer.getChildren().listIterator().next();
            splitPane.setStyle("-fx-background-color: #efefef");
            BorderPane navigation  = initNavigationBar();
            navigation.setMaxWidth(200.0);

            splitPane.getItems().add(navigation);
            BorderPane body = initBody();
            body.setMinWidth(primaryStage.getWidth() - navigation.getWidth());
            splitPane.getItems().add(body);

            Scene scene = new Scene(this.rootContainer);
            scene.getStylesheets().add(css);


            primaryStage.setScene(scene);
            primaryStage.setMinWidth(1000.0);
            primaryStage.setMinHeight(500.0);
            primaryStage.show();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
    private BorderPane initNavigationBar (){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("views/layout/navigation-bar.fxml"));
            return loader.load();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    private BorderPane initBody () {
        try {
            return FXMLLoader.load(ResourceUtil.getViewUrl("dashboard.fxml"));
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }
}
/*
//LINE
         NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Créer le LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Exemple de LineChart");

        // Créer les données pour la première série
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>(1, 10));
        series1.getData().add(new XYChart.Data<>(2, 20));
        series1.getData().add(new XYChart.Data<>(3, 15));
        series1.setName("Série 1");

        // Créer les données pour la deuxième série
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>(1, 15));
        series2.getData().add(new XYChart.Data<>(2, 10));
        series2.getData().add(new XYChart.Data<>(3, 25));
        series2.setName("Série 2");

        // Ajouter les séries au LineChart
        lineChart.getData().addAll(series1, series2);

        // Personnaliser les couleurs des lignes
        String colorStyle1 = "-fx-stroke: #FF0000;"; // Rouge
        String colorStyle2 = "-fx-stroke: #00FF00;"; // Vert
        lineChart.lookup(".chart-series-line.series0").setStyle(colorStyle1);
        lineChart.lookup(".chart-series-line.series1").setStyle(colorStyle2);

        // Créer la scène
        Scene scene = new Scene(lineChart, 600, 400);

        // Configurer la scène et afficher la fenêtre
        stage.setScene(scene);
        stage.setTitle("JavaFX LineChart Example with Multiple Lines");
        stage.show();


        BAR

                CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Créer le BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Exemple de BarChart");

        // Créer les données
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Catégorie 1", 10));
        series.getData().add(new XYChart.Data<>("Catégorie 2", 20));
        series.getData().add(new XYChart.Data<>("Catégorie 3", 15));

        // Ajouter les données au BarChart
        barChart.getData().add(series);

        // Personnaliser les couleurs des barres
        for (XYChart.Data<String, Number> data : series.getData()) {
            String category = data.getXValue();
            if (category.equals("Catégorie 1")) {
                data.getNode().setStyle("-fx-bar-fill: #FF0000;"); // Rouge
            } else if (category.equals("Catégorie 2")) {
                data.getNode().setStyle("-fx-bar-fill: #00FF00;"); // Vert
            } else if (category.equals("Catégorie 3")) {
                data.getNode().setStyle("-fx-bar-fill: #0000FF;"); // Bleu
            }
        }

        // Créer la scène
        Scene scene = new Scene(barChart, 600, 400);

        // Configurer la scène et afficher la fenêtre
        stage.setScene(scene);
        stage.setTitle("JavaFX BarChart Example with Custom Colors");
        stage.show();


        PIE CHART

                // Créer des données pour le PieChart
        // Créer les axes
        // Créer les axes
        // Créer les axes
        // Créer des données pour le PieChart
        PieChart.Data slice1 = new PieChart.Data("Catégorie 1", 30);
        PieChart.Data slice2 = new PieChart.Data("Catégorie 2", 45);
        PieChart.Data slice3 = new PieChart.Data("Catégorie 3", 25);

        // Créer le PieChart
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(slice1, slice2, slice3);
        pieChart.setTitle("Exemple de PieChart");

        // Créer la scène
        Scene scene = new Scene(pieChart, 600, 400);

        // Configurer la scène et afficher la fenêtre
        stage.setScene(scene);
        stage.setTitle("JavaFX PieChart Example");
        stage.show();
 */
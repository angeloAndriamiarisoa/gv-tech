package com.techimage.projectjfx;

import com.techimage.projectjfx.controller.RootApp;
import com.techimage.projectjfx.interfaces.InitApplicationInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class InitApplication  {
    public static Stage primaryStage;
    private BorderPane rootContainer;
    public InitApplication  (Stage stage) {
        Application.setUserAgentStylesheet(MainApp.class.getResource("assets/css/primer-light.css").toExternalForm());
        this.primaryStage = stage;

    }

    public void init () {
        Image icon = new Image(MainApp.class
                .getResource("assets/icon/techimage.jpg")
                .toExternalForm());

        this.primaryStage.setTitle("TechStock");
        this.primaryStage.getIcons().add(icon);
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
            body.setMinWidth(this.primaryStage.getWidth() - navigation.getWidth());
            splitPane.getItems().add(body);

            Scene scene = new Scene(this.rootContainer);
            scene.getStylesheets().add(css);

            this.primaryStage.setScene(scene);
            this.primaryStage.setMinWidth(1000.0);
            this.primaryStage.setMinHeight(500.0);
            this.primaryStage.show();
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
            BorderPane pane = loader.load();
            return   pane;
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    private BorderPane initBody () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/layout/body.fxml"));
            return loader.load();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }
}

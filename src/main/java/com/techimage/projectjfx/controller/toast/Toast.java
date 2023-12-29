package com.techimage.projectjfx.controller.toast;


import com.techimage.projectjfx.util.ResourceUtil;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static com.techimage.projectjfx.InitApplication.primaryStage;
public class Toast {
    public static String SUCCESS = "success.png";
    public static String ERROR = "error.png";
    public static String WARNING = "warning.png";
    public static void start(String message, String messageType) {
        // Crée une étiquette pour le contenu du toast
        Label toastLabel = new Label(message);
        ImageView imageView = new ImageView();
        String url = ResourceUtil.getIconUrl(messageType).toExternalForm();

        Image image = new Image(url);
        imageView.setImage(image);
        imageView.setFitHeight(40.0);
        imageView.setFitWidth(40.0);
        toastLabel.setGraphic(imageView);
        toastLabel.setPrefHeight(50.0);
        toastLabel.setPrefWidth(230.0);

        Stage toastStage = new Stage();
        toastStage.initStyle(StageStyle.UNDECORATED);
        toastStage.setAlwaysOnTop(true);
        toastStage.setMinHeight(50.0);
        toastStage.setMinWidth(250.0);
        StackPane content = new StackPane(toastLabel);

        toastStage.setScene(new Scene(content, Color.TRANSPARENT));

        toastStage.setOpacity(0.9);

        toastStage.setX(primaryStage.getX() + primaryStage.getWidth() - toastStage.getMinWidth() - 15.0);
        toastStage.setY(primaryStage.getY() + 40.0);

        Duration displayTime = Duration.seconds(3);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0)),
                new KeyFrame(new Duration(200), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1)),
                new KeyFrame(displayTime, new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1)),
                new KeyFrame(displayTime.add(Duration.millis(200 )), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0))
        );

        timeline.setOnFinished(event -> toastStage.hide());
        toastStage.show();
        timeline.play();

    }
}






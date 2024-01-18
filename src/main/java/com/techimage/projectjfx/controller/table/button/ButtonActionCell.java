package com.techimage.projectjfx.controller.table.button;

import com.techimage.projectjfx.util.ResourceUtil;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ButtonActionCell {


    protected Button button;
    protected void setButton (String title, String style,
                             String hoverStyle, String icon) {
        button = new Button();
        button.setText(title);
        ImageView imageView = new ImageView();
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(19.0);
        imageView.setFitHeight(19.0);
        Image image = new Image(ResourceUtil.getIconUrl(icon).toExternalForm());
        imageView.setImage(image);
        imageView.setOpacity(0.8);

        button.setStyle(style);
        button.setGraphic(imageView);
        button.setMinWidth(100.0);

        button.setOnMouseEntered(mouseEvent -> {
            imageView.setOpacity(1.0);
            button.setStyle(hoverStyle);
        });
        button.setOnMouseExited(mouseEvent -> {
            button.setStyle(style);
            imageView.setOpacity(0.8);
        });

    }

    public Button getButton() {
        return this.button;
    }
}

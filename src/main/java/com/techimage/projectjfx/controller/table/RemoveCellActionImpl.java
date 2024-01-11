package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.util.ResourceUtil;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class RemoveCellActionImpl implements ActionInterface {

    private Button addButton;
    private HBox actionCell;

    public HBox getActionCell() {
        return actionCell;
    }
    public RemoveCellActionImpl () {
        String add = "-fx-border-color: #faf16c; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-weight : 600";
        String addHover = "-fx-border-color: #eee000; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                "-fx-font-weight : 600";
        String editIcon = "delete.png";
        this.addButton = makeActionButton("", add, addHover, editIcon);
        actionCell =new HBox(addButton);
        actionCell.setSpacing(5.0);

        this.addButton.setOnMouseClicked(mouseEvent -> {
            this.removeAction();
        });

    }

    @Override
    public Button makeActionButton(String title, String style, String hoverStyle, String icon) {
        Button button = new Button(title);

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

        return button;
    }

    public void removeAction() {

    }
}

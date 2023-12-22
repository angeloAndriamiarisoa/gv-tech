package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CustomerActionCellImpl extends TableCell<Customer, String> implements CustomerActionCell{
    private Button editButton;
    private Button deleteButton;
    private HBox actionCell;

    public CustomerActionCellImpl() throws IOException {

        String editStyle = "-fx-background-color: #f5ea1f;" +
                " -fx-font-weight : 600";
        String editHoverStyle = "-fx-background-color: #efe44b;" +
                "-fx-font-weight : 600";

        String deleteStyle = "-fx-background-color: #f51a1a;" +
                "-fx-font-weight : 600";
        String deleteHoverStyle = "-fx-background-color: #f64646;" +
                "-fx-font-weight : 600";

        String editIcon = "delete.png";
        String deleteIcon = "delete.png";

        this.editButton = makeActionButton("Modifier", editStyle, editHoverStyle,  editIcon);
        this.deleteButton = makeActionButton("Supprimer", deleteStyle, deleteHoverStyle,  deleteIcon);

        actionCell =new HBox(editButton, deleteButton);
        actionCell.setSpacing(5.0);
        setGraphic(this.actionCell);

        this.editButton.setOnMouseClicked(mouseEvent -> {
            this.edit();
        });

        this.deleteButton.setOnMouseClicked(mouseEvent -> {
            this.delete();
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(this.actionCell);
        }
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

        button.setStyle(style);
        button.setGraphic(imageView);
        button.setMinWidth(100.0);

        button.setOnMouseEntered(mouseEvent -> {
            button.setStyle(hoverStyle);

        });
        button.setOnMouseExited(mouseEvent -> {
            button.setStyle(style);

        });

        return button;
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }
}

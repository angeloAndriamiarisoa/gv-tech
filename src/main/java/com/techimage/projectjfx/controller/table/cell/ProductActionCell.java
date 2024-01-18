package com.techimage.projectjfx.controller.table.cell;
import com.techimage.projectjfx.controller.table.ActionCell;
import com.techimage.projectjfx.controller.table.button.DeleteButton;
import com.techimage.projectjfx.controller.table.button.EditButton;
import com.techimage.projectjfx.controller.table.button.PurchaseButton;
import com.techimage.projectjfx.controller.table.event.ProductEvent;
import com.techimage.projectjfx.model.Product;
import javafx.scene.control.Button;

public class ProductActionCell extends ActionCell<Product> implements ProductEvent {
    public ProductActionCell() {
        Button editButton = new EditButton().getButton();
        editButton.setOnMouseClicked(mouseEvent -> edit());

        Button deleteButton = new DeleteButton().getButton();
        deleteButton.setOnMouseClicked(mouseEvent -> delete());

        Button purchageButton = new PurchaseButton().getButton();
        purchageButton.setOnMouseClicked(mouseEvent -> purchase());
        setActionCell(purchageButton, editButton, deleteButton);
    }


    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void add() {

    }

    @Override
    public void purchase() {

    }

}

package com.techimage.projectjfx.controller.table.cell;
import com.techimage.projectjfx.controller.table.ActionCell;
import com.techimage.projectjfx.controller.table.button.DeleteButton;
import com.techimage.projectjfx.controller.table.button.EditButton;
import com.techimage.projectjfx.controller.table.button.PurchaseButton;
import com.techimage.projectjfx.controller.table.event.CustomerEvent;
import com.techimage.projectjfx.controller.table.event.ProductEvent;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.model.Product;
import javafx.scene.control.Button;

public class CustomerActionCell extends ActionCell<Customer> implements CustomerEvent {
    public CustomerActionCell() {
        Button editButton = new EditButton().getButton();
        editButton.setOnMouseClicked(mouseEvent -> edit());

        Button deleteButton = new DeleteButton().getButton();
        deleteButton.setOnMouseClicked(mouseEvent -> delete());

        setActionCell(editButton, deleteButton);
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
}

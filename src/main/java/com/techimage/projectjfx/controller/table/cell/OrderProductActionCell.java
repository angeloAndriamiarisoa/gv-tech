package com.techimage.projectjfx.controller.table.cell;
import com.techimage.projectjfx.controller.table.ActionCell;
import com.techimage.projectjfx.controller.table.button.AddButton;
import com.techimage.projectjfx.controller.table.button.DeleteButton;
import com.techimage.projectjfx.controller.table.button.EditButton;
import com.techimage.projectjfx.controller.table.event.BasicEvent;
import com.techimage.projectjfx.controller.table.event.CustomerEvent;
import com.techimage.projectjfx.model.Product;
import javafx.scene.control.Button;

public class OrderProductActionCell extends ActionCell<Product> implements BasicEvent {
    public OrderProductActionCell() {
        Button addButton = new AddButton().getButton();
        addButton.setOnMouseClicked(mouseEvent -> add());
        setActionCell(addButton);
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

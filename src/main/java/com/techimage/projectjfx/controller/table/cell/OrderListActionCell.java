package com.techimage.projectjfx.controller.table.cell;
import com.techimage.projectjfx.controller.table.ActionCell;
import com.techimage.projectjfx.controller.table.button.DeleteButton;
import com.techimage.projectjfx.controller.table.button.EditButton;
import com.techimage.projectjfx.controller.table.event.BasicEvent;
import com.techimage.projectjfx.model.Order;
import com.techimage.projectjfx.model.OrderTableView;
import com.techimage.projectjfx.model.Product;
import javafx.scene.control.Button;

public class OrderListActionCell extends ActionCell<OrderTableView> implements BasicEvent {
    public OrderListActionCell() {
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

package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.controller.table.button.DeleteButton;
import com.techimage.projectjfx.controller.table.button.EditButton;
import com.techimage.projectjfx.controller.table.button.PurchaseButton;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionCell<M> extends TableCell<M, String> {
    private HBox actionCell;

    public void setActionCell (Button... actionsButton) {
        actionCell =new HBox(actionsButton);
        actionCell.setSpacing(5.0);
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
}

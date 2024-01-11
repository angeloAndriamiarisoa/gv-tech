package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.model.OrderTableView;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class OrderListActionCellImpl extends TableCell<OrderTableView, String>
                                            implements RemoveCellEvent {
    private HBox actionCell;

    private OrderListActionCellImpl _this = this;

    public OrderListActionCellImpl() {
        RemoveCellActionImpl actionsCell = null;
        actionsCell = new RemoveCellActionImpl() {
            @Override
            public void removeAction() {
                _this.remove();
            }
        };
        this.actionCell = actionsCell.getActionCell();
        setGraphic(this.actionCell);

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
    public void remove() {

    }
}

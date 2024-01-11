package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.model.Product;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ProductSingleActionCellImpl extends TableCell<Product, String>
                                    implements AddCellEvent{
    private HBox actionCell;

    private ProductSingleActionCellImpl _this = this;

    public ProductSingleActionCellImpl() {
        AddActionCellImpl actionsCell = null;
        actionsCell = new AddActionCellImpl() {

            @Override
            public void addAction() {
                _this.add();
            }
        };
        this.actionCell = actionsCell.getActionCell();
        setGraphic(this.actionCell);

    }
    @Override
    public void add() {

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

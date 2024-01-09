package com.techimage.projectjfx.controller.table;

import com.techimage.projectjfx.InitApplication;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ProductActionCellImpl extends TableCell<Product, String>
        implements ActionCellEvent {
    private HBox actionCell;

    private ProductActionCellImpl _this = this;

    public ProductActionCellImpl()  {
        ActionCellImpl actionsCell = null;
        try {
            actionsCell = new ActionCellImpl() {
                @Override
                public void deleteAction() {
                    _this.delete();
                }

                @Override
                public void editAction() {
                    _this.edit();
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public void edit() {

    }
    @Override
    public void delete() {

    }
}

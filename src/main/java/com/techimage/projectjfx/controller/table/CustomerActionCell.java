package com.techimage.projectjfx.controller.table;

import javafx.scene.control.Button;


public interface CustomerActionCell extends ActionCellEvent {
    public Button makeActionButton (String title, String style, String hoverStyle, String icon);
}

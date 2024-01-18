package com.techimage.projectjfx.controller.table.button;

public class PurchaseButton extends ButtonActionCell {
    public PurchaseButton() {
        String edit = "-fx-border-color: #80ff80; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-weight : 600";
        String editHoverStyle = "-fx-background-color: #4dff4d;" +
                "-fx-font-weight : 600";
        String editIcon = "plus.png";

        String title = "";
        setButton(title, edit, editHoverStyle, editIcon);
    }
}

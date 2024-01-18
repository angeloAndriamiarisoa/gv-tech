package com.techimage.projectjfx.controller.table.button;

public class DeleteButton extends ButtonActionCell {
    public DeleteButton() {
        String edit = "-fx-border-color: #fa6c6c; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-weight : 600";
        String editHoverStyle = "-fx-background-color: #ee0000;" +
                "-fx-font-weight : 600";
        String editIcon = "delete.png";

        String title = "";
        setButton(title, edit, editHoverStyle, editIcon);
    }
}

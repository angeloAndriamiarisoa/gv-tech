package com.techimage.projectjfx.controller.table.button;

public class EditButton extends ButtonActionCell {
    public EditButton() {
        String edit = "-fx-border-color: #faf16c; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-weight : 600";
        String editHoverStyle = "-fx-background-color: #efe44b;" +
                "-fx-font-weight : 600";
        String editIcon = "edit.png";

        String title = "";
        setButton(title, edit, editHoverStyle, editIcon);
    }
}

package com.techimage.projectjfx.controller.table.button;

public class AddButton extends ButtonActionCell {
    public AddButton() {
        String edit = "-fx-border-color: #8080ff; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-weight : 600";
        String editHoverStyle = "-fx-background-color: #6666ff;" +
                "-fx-font-weight : 600";
        String editIcon = "plus.png";

        String title = "";
        setButton(title, edit, editHoverStyle, editIcon);
    }
}

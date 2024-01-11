package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {


    @FXML
    public TableView<Product> productTableView;

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

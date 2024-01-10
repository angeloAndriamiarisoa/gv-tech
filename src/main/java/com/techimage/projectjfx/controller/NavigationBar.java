package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.controller.navigation.NavigationUtil;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class NavigationBar {

    @FXML
    public VBox nav;
    @FXML
    public Label dashboardItem;
    @FXML
    public Label customerItem;
    @FXML
    public Label productItem;

    @FXML
    public Label saleItem;

    @FXML
    public void toDashboard() throws IOException {
        BorderPane dashboard =  FXMLLoader.load(ResourceUtil.getViewUrl("dashboard.fxml"));
        this.navigate(this.dashboardItem, dashboard);
    }

    @FXML
    public void toCustomer() throws IOException {
        BorderPane customer =  FXMLLoader.load(ResourceUtil.getViewUrl("customer.fxml"));
        this.navigate(this.customerItem, customer);
    }

    @FXML
    public void toProduct() throws IOException {
        BorderPane product =  FXMLLoader.load(ResourceUtil.getViewUrl("product.fxml"));
        this.navigate(this.productItem, product);
    }

    @FXML
    public void toSale()throws IOException {
        BorderPane product =  FXMLLoader.load(ResourceUtil.getViewUrl("sale.fxml"));
        this.navigate(this.saleItem, product);
    }

    private void navigate (Label navItem, BorderPane destination) {
        NavigationUtil.navigate(this.nav, navItem, destination);
    }
}

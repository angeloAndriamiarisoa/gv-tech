package com.techimage.projectjfx;

import com.techimage.projectjfx.controller.SplitBody;
import com.techimage.projectjfx.exception.DatabaseException;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.CustomerRepository;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.util.DbUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApp extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        InitApplication init = new InitApplication(stage);
        init.init();

        /*if(new CustomerRepository().countAll() == 0) {
            Integer phone = 0341010000;

            for(int i = 0; i<28; i++) {
                phone++;

                new CustomerRepository().save(new Customer("client" + i,phone.toString(),"email" + i + "@gmail.com"));
            }
        }*/

        if(new ProductRepository().countAll() == 0) {
            Integer phone = 10;

            for(int i = 0; i<12; i++) {
                phone++;
                Product product = new Product();
                product.setId("id" + i);
                product.setName("product" + i);
                product.setUnitPrice(phone*i);
                product.setUnit("piece");
                new ProductRepository().save(product);
            }
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
package com.techimage.projectjfx;

import com.techimage.projectjfx.controller.SplitBody;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.repository.CustomerRepository;
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

public class MainApp  {

   /*@Override
    public void start(Stage stage) throws IOException {
        InitApplication init = new InitApplication(stage);
        init.init();
    }*/


    public static void main(String[] args) {
        new CustomerRepository().save(new Customer("angelo12", "phone1200D", "emial1D0012"));
        new CustomerRepository().save(new Customer("angelo12", "phone12D", "emi1D0012"));
        //      new CustomerRepository().update(new Customer("angelo122XXXDD", "phone12", "emial12ko"), "phone12");

       System.out.println(new CustomerRepository().findById("phone12D"));
      // System.out.println(new CustomerRepository().findAll());



        return;
       // launch();
    }
}
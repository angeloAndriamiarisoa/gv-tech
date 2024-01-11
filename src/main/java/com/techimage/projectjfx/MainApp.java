package com.techimage.projectjfx;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.techimage.projectjfx.controller.SplitBody;
import com.techimage.projectjfx.exception.DatabaseException;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.CustomerRepository;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.util.DbUtil;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.io.image.ImageDataFactory;

public class MainApp extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
       InitApplication init = new InitApplication(stage);
     init.init();

      if(new CustomerRepository().countAll() == 0) {
            Integer phone = 0341010000;

            for(int i = 0; i<5 ; i++) {
                phone++;

                new CustomerRepository().save(new Customer("client" + i,phone.toString(),"email" + i + "@gmail.com"));
            }
        }

        if(new ProductRepository().countAll() == 0) {
            Integer phone = 10;

            for(int i = 0; i<5; i++) {
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
    public static void main(String[] args)  {
      //  launch();

    }
}
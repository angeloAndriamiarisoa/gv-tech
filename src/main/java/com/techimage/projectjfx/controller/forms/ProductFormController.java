package com.techimage.projectjfx.controller.forms;

import com.techimage.projectjfx.EventBtn;
import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.exception.ValidationException;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.random.RandomGenerator;

public class ProductFormController implements Initializable {
    public static Product productToEdit;

    @FXML
    public Label title;
    @FXML
    public AnchorPane productFormContainer;
    @FXML
    @TextValidation(label = "Nom",
            message = "Veuillez à entrer un  nom de produit correct",
            regex = "^[a-zA-ZéèêÉÈÊ'0-9 ]+$")
    public TextField nameTxt;

    @FXML
    @TextValidation(label = "Unité",
            message = "Veuillez à choisir une unité pour le produit",
            regex = "^[a-zA-ZéèêÉÈÊ'0-9 ]+$")
    public ChoiceBox unitTxt;
    @FXML
    @TextValidation(label = "Prix unitaire",
            message = "Le prix unitaire doit être en chiffre",
            regex = "^[0-9]+$")
    public TextField unitPriceTxt;
    @FXML public Label unitTxtError;
    @FXML public Label unitPriceTxtError;
    @FXML public Label nameTxtError;

    @FXML
    public TextField quantityTxt;

    @FXML public Label quantityLabel;

    @FXML
    public Button saveBtn;
    @FXML
    public Button resetBtn;

    private ProductFormController _this = this;

    private ProductRepository productRepository = new ProductRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> choice = new ArrayList<>();
        choice.add("Pièce                ");
        choice.add("Carton               ");
        unitTxt.setItems(FXCollections.observableList(choice));
        quantityTxt.setVisible(false);
        this.setTextFields();

        EventBtn eventBtn = new EventBtn() {
            @Override
            public void save() {
                _this.save();
            }

            @Override
            public void reset() {
                nameTxt.clear();
                unitPriceTxt.clear();
                unitTxt.getSelectionModel().select(null);
            }
        };


        saveBtn.setOnMouseClicked(mouseEvent -> {
            eventBtn.save();
        });

        resetBtn.setOnMouseClicked(mouseEvent -> {
            eventBtn.reset();
        });


    }
    private void save () {
        ValidationUtil validation = new ValidationUtil(_this);
        try {
            try {
                validation.textValidation();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            Product product = new Product();
            product.setName(nameTxt.getText());
            product.setUnit(unitTxt.getValue().toString());
            product.setUnitPrice(Integer.parseInt(unitPriceTxt.getText()));
            if(productToEdit == null) {
                product.setId(nameTxt.getText(1,5));
                this.productRepository.save(product);
                productToEdit = null;
                Toast.start("Ajout produit fini!", Toast.SUCCESS);
            }
            else {
                product.setId(productToEdit.getId());

                this.productRepository.update(product, productToEdit.getId());
                productToEdit = null;
                Toast.start("Modification client fini!", Toast.SUCCESS);
            }

            Dialog.dialog.hide();

        }
        catch (ValidationException exception) {
            // throw new RuntimeException(exception);
            System.out.println("erruer sur la validation");
        }
    }

    public void setTextFields () {
        quantityLabel.setVisible(false);
        if(productToEdit != null) {
            this.title.setText("Modifier un client : " + productToEdit.getName());
            nameTxt.setText(productToEdit.getName());
            unitTxt.getSelectionModel().select(productToEdit.getUnit());
            unitPriceTxt.setText(productToEdit.getUnitPrice().toString());
            quantityTxt.setText(productToEdit.getQuantity().toString());
            quantityLabel.setVisible(true);
            quantityTxt.setVisible(true);
            quantityTxt.setEditable(false);
        }
    }
}

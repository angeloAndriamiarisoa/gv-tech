package com.techimage.projectjfx.controller.forms;

import com.techimage.projectjfx.EventBtn;
import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.exception.ValidationException;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public static Customer customerToEdit;

    @FXML
    @TextValidation(label = "Nom",
            message = "La valeur entrée ne correspond pas au norme de nom",
            regex = "\\\\d{3}-\\\\d{2}-\\\\d{4}",
            minLength = 5)
    public TextField nameTxt;
    @FXML
    @TextValidation(label = "Téléphone",
            message = "La valeur entrée ne correspond pas au numéro téléphone existant",
            regex = "\\\\d{3}-\\\\d{2}-\\\\d{4}",
            minLength = 5)
    public TextField phoneTxt;
    @FXML
    @TextValidation(label = "Email",
            message = "La valeur entrée ne correspond pas au norme d'adresse email",
            regex = "\\\\d{3}-\\\\d{2}-\\\\d{4}",
            minLength = 5)
    public TextField emailTxt;
    @FXML
    public Button saveBtn;
    @FXML
    public Button resetBtn;

    @FXML
    public Label nameTxtError;
    @FXML
    public Label phoneTxtError;
    @FXML
    public Label emailTxtError;

    @FXML
    public Label title;
    private CustomerFormController addCustomerController = this;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setTextFields();
        EventBtn eventBtn = new EventBtn() {
            @Override
            public void edit() {

            }

            @Override
            public void save() {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Information");
               alert.setHeaderText("");
               alert.setContentText("Ajout");

               alert.show();
               customerToEdit = null;

            }

            @Override
            public void reset() {
                nameTxt.clear();
                phoneTxt.clear();
                emailTxt.clear();
                nameTxtError.setText("");
                phoneTxtError.setText("");
                emailTxtError.setText("");
            }
        };

        this.resetBtn.setOnMouseClicked(mouseEvent -> {
            eventBtn.reset();
        });

        this.saveBtn.setOnMouseClicked(mouseEvent -> {
            this.save();
        });
    }

    private void save () {
        ValidationUtil validation = new ValidationUtil(addCustomerController);
        try {
            validation.textValidation();
            //Dao save
        }
        catch (ValidationException exception) {
            System.out.println(validation.getErrors());
            System.out.println(validation.getErrors().keySet());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTextFields () {
        if(customerToEdit != null) {
            this.title.setText("Modifier un client : " + customerToEdit.getName());
            nameTxt.setText(customerToEdit.getName());
            emailTxt.setText(customerToEdit.getEmail());
            phoneTxt.setText(customerToEdit.getPhone());
        }
    }


}

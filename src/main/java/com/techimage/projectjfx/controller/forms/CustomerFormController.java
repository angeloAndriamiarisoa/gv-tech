package com.techimage.projectjfx.controller.forms;

import com.techimage.projectjfx.EventBtn;
import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.controller.CustomerController;
import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.exception.ValidationException;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.repository.CustomerRepository;
import com.techimage.projectjfx.util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public static Customer customerToEdit;

    @FXML
    public AnchorPane customerFormContainer;

    @FXML
    @TextValidation(label = "Nom",
            message = "La valeur entrée ne correspond pas au norme de nom",
            regex = "^[a-zA-ZéèêÉÈÊ' ]+$",
            minLength = 5)
    public TextField nameTxt;
    @FXML
    @TextValidation(label = "Téléphone",
            message = "La valeur entrée ne correspond pas au numéro téléphone existant",
            regex = "^(034|032|033|038|039)\\d{7}$")
    public TextField phoneTxt;
    @FXML
    @TextValidation(label = "Email",
            message = "La valeur entrée ne correspond pas au norme d'adresse email",
            regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",
            required = false)
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
    private CustomerRepository customerRepository = new CustomerRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setTextFields();
        EventBtn eventBtn = new EventBtn() {
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
            Customer customer = new Customer();
            customer.setEmail(emailTxt.getText());
            customer.setName(nameTxt.getText());
            customer.setPhone(phoneTxt.getText());
            if(customerToEdit == null) {
                this.customerRepository.save(customer);
                customerToEdit = null;
                Toast.start("Ajout client fini!", Toast.SUCCESS);

            }
            else {
                this.customerRepository.update(customer, customerToEdit.getPhone());
                customerToEdit = null;
                Toast.start("Modification client fini!", Toast.SUCCESS);
            }

            Dialog.dialog.hide();

        }
        catch (ValidationException exception) {
           // throw new RuntimeException(exception);
            System.out.println("erruer sur la validation");
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

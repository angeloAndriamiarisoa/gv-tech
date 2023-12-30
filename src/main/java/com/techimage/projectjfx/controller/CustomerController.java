package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.table.CustomerActionCellImpl;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.repository.CustomerRepository;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import static com.techimage.projectjfx.controller.forms.CustomerFormController.customerToEdit;

public class CustomerController implements Initializable {


    @FXML
    public TableColumn<Customer, String> nameColumn;
    @FXML
    public TableColumn<Customer, String> phoneColumn;
    @FXML
    public TableColumn<Customer, String> emailColumn;
    @FXML
    public TableColumn<Customer, String> actionsColumn;
    @FXML
    public TableView<Customer> tableCustomer;

    @FXML
    public Button btnShowDialog;

    private ObservableList<Customer> customerList;

    public static Stage dialog;

    private CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillTable();
        initBtnShowDialog();
    }

    private void initBtnShowDialog() {
        this.btnShowDialog.setOnMouseClicked(mouseEvent -> {
            try {
                showDialog();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void showDialog() throws IOException {

        AnchorPane content = FXMLLoader.load(ResourceUtil.getViewFormUrl("customer-form.fxml"));

        Dialog.showDialog("ajout", content, new Function() {
            @Override
            public Object apply(Object o) {
                customerToEdit = null;
                CustomerController.this.fillTable();
                return null;
            }
        });
    }

    private void fillTable () {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        customerList = FXCollections.observableArrayList(this.customerRepository.findAll());
        tableCustomer.setItems(customerList);
        actionsColumn.setCellFactory(param -> {

            try {
                return new CustomerActionCellImpl() {
                    @Override
                    public void edit() {
                        tableCustomer.getSelectionModel().select(this.getIndex());
                        customerToEdit = tableCustomer.getSelectionModel().getSelectedItem();
                        try {
                            showDialog();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void delete() {
                        tableCustomer.getSelectionModel().select(this.getIndex());
                        Customer customer = tableCustomer.getSelectionModel().getSelectedItem();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("");
                        alert.setContentText("Voulez vous vraiment supprimer ce client");
                        alert.showAndWait();
                        if(alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                            customerRepository.delete(customer.getPhone());
                            Toast.start("Client supprimé!", Toast.SUCCESS);
                            fillTable();
                        }

                    }
                };
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

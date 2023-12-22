package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.controller.table.CustomerActionCellImpl;
import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public Button btnShowAddDialog;

    private ObservableList<Customer> customerList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        customerList = FXCollections.observableArrayList(
                new Customer("John Doe", "123-456-7890", "john.doe@example.com"),
                new Customer("Jane Smith", "987-654-3210", "jane.smith@example.com"),
                new Customer("John Doe", "123-456-7890", "john.doe@example.com"),
                new Customer("Jane Smith", "987-654-3210", "jane.smith@example.com"),
                new Customer("John Doe", "123-456-7890", "john.doe@example.com")
        );
        tableCustomer.setItems(customerList);
        actionsColumn.setCellFactory(param -> {
            try {
                return new CustomerActionCellImpl() {
                    @Override
                    public void edit() {
                        tableCustomer.getSelectionModel().select(this.getIndex());
                        customerToEdit = tableCustomer.getSelectionModel().getSelectedItem();
                        try {
                            showEditDialog();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void delete() {
                        super.delete();
                    }
                };
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        initBtnShowAddDialog();

    }

    private void initBtnShowAddDialog () {
        this.btnShowAddDialog.setOnMouseClicked(mouseEvent -> {
            try {
                showAddDialog();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void showAddDialog () throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Mon dialog");

        AnchorPane content = FXMLLoader.load(ResourceUtil.getViewFormUrl("customer-form.fxml"));
        dialog.setResizable(false);
        Scene scene = new Scene(content);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showEditDialog () throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Mon dialog");

        AnchorPane content = FXMLLoader.load(ResourceUtil.getViewFormUrl("customer-form.fxml"));
        dialog.setResizable(false);
        Scene scene = new Scene(content);
        dialog.setScene(scene);
        dialog.show();
    }



}

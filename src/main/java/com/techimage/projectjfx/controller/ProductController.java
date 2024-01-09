package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.dialog.OwnAlert;
import com.techimage.projectjfx.controller.table.CustomerActionCellImpl;
import com.techimage.projectjfx.controller.table.ProductActionCellImpl;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.util.ResourceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import static com.techimage.projectjfx.controller.forms.ProductFormController.productToEdit;

public class ProductController implements Initializable {

    @FXML
    public BorderPane productContainer;

    @FXML
    public TableView<Product> tableProduct;

    @FXML
    public TableColumn<Product, String> idColumn;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, String> unitColumn;
    @FXML
    public TableColumn<Product, String> unitPriceColumn;
    @FXML
    public TableColumn<Product, String> quantityColumn;

    @FXML
    public TableColumn<Product, String> actionsColumn;
    @FXML
    public Button btnShowDialog;

    @FXML
    public Pagination pagination;

    private ObservableList<Product> productList;

    public static Stage dialog;
    private ProductRepository productRepository = new ProductRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         fillTable(1);
        initBtnShowDialog();
        int totalItems = this.productRepository.countAll();
        com.techimage.projectjfx.controller.pagination.Pagination.setPaginationFactory(
                pagination,
                totalItems
        );
        pagination.currentPageIndexProperty().addListener((observableValue, oldValue, newValue) -> {
            fillTable((Integer) newValue + 1);
        });


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

        AnchorPane content = FXMLLoader.load(ResourceUtil.getViewFormUrl("product-form.fxml"));

        Dialog.showDialog("Produit", content, new Function() {
            @Override
            public Object apply(Object o) {
                productToEdit = null;
                ProductController.this.fillTable(pagination.getCurrentPageIndex() + 1);
                return null;
            }
        });
    }
    private void fillTable (Integer page) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productList = FXCollections.observableArrayList(this.productRepository.findAll(page));
        tableProduct.setItems(productList);
        actionsColumn.setCellFactory(param -> {
            return new ProductActionCellImpl() {
                @Override
                public void edit() {
                    tableProduct.getSelectionModel().select(this.getIndex());
                    productToEdit = tableProduct.getSelectionModel().getSelectedItem();
                    try {
                        showDialog();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void delete() {
                    tableProduct.getSelectionModel().select(this.getIndex());
                    Product product = tableProduct.getSelectionModel().getSelectedItem();
                    ButtonBar.ButtonData result = OwnAlert
                            .optionalAlert("Voulez-vous rétirer le produit :  XX ?");
                    if(result == ButtonBar.ButtonData.OK_DONE) {
                        productRepository.delete(product.getId());
                        Toast.start("Produit : xxx supprimé !", Toast.SUCCESS);
                        System.out.println("current page index-->" + pagination.getCurrentPageIndex());
                        fillTable(pagination.getCurrentPageIndex() + 1
                        );
                    }
                }
            };
        });
    }

}

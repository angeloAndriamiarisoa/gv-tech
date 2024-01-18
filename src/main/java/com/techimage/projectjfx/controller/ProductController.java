package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.dialog.OwnAlert;
import com.techimage.projectjfx.controller.table.cell.ProductActionCell;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.model.Purchase;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.repository.PurchaseRepository;
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
import static com.techimage.projectjfx.controller.forms.PurchaseFormController.productToPurchage;

public class ProductController implements Initializable {

    @FXML
    public BorderPane productContainer;

    @FXML Tab purchaseTab;

    @FXML
    public TableView<Product> tableProduct;

    @FXML TableView<Purchase> purchaseTableView;

    @FXML TableColumn<Purchase,String> idHistoricColumn;
    @FXML TableColumn<Purchase,String> productHistoricColumn;
    @FXML TableColumn<Purchase,String> quantityHistoricColumn;
    @FXML TableColumn<Purchase,String> createdAtHistoricColumn;


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
    @FXML Pagination paginationPurchase;

    private ObservableList<Product> productList;
    private ObservableList<Purchase> purchaseObservableList;

    public static Stage dialog;
    private ProductRepository productRepository = new ProductRepository();
    private PurchaseRepository purchaseRepository = new PurchaseRepository();
    private ProductController _this = this;
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

        int totalItemsPurchase = productRepository.countAll();
        com.techimage.projectjfx.controller.pagination.Pagination.setPaginationFactory(
                paginationPurchase,
                totalItemsPurchase
        );
        paginationPurchase.currentPageIndexProperty().addListener((observableValue, oldValue, newValue) -> {
            fillPurchaseTable((Integer) newValue + 1);
        });

        purchaseTab.setOnSelectionChanged(event -> fillPurchaseTable(1));

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
                _this.fillTable(pagination.getCurrentPageIndex() + 1);
                return null;
            }
        });
    }

    private void showPurchaseDialog() throws IOException {

        AnchorPane content = FXMLLoader.load(ResourceUtil.getViewFormUrl("purchase-form.fxml"));

        Dialog.showDialog("Approvisionnement", content, new Function() {
            @Override
            public Object apply(Object o) {
                productToPurchage = null;
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

        idColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        nameColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.2));
        unitColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        unitPriceColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        quantityColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        actionsColumn.minWidthProperty().bind(tableProduct.widthProperty().multiply(0.3));
        productList = FXCollections.observableArrayList(this.productRepository.findAll(page));
        tableProduct.setItems(productList);
        actionsColumn.setCellFactory(param -> {

            return new ProductActionCell() {
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
                            .optionalAlert("Voulez-vous rétirer le produit : "
                                    + product.getName() +" ?");
                    if(result == ButtonBar.ButtonData.OK_DONE) {
                        productRepository.delete(product.getId());
                        Toast.start("Produit : "+ product.getName() +" supprimé !",
                                Toast.SUCCESS);
                        fillTable(pagination.getCurrentPageIndex() + 1
                        );
                    }
                }

                @Override
                public void purchase() {
                    tableProduct.getSelectionModel().select(this.getIndex());
                    productToPurchage = tableProduct.getSelectionModel().getSelectedItem();
                    System.out.println(productToPurchage);
                    try {
                        showPurchaseDialog();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            };
       //     return new ProductActionCellImpl() {

        //    };
        });
    }
    private void fillPurchaseTable (Integer page) {
        idHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        createdAtHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        idHistoricColumn.minWidthProperty().bind(purchaseTableView.widthProperty().multiply(0.2));
        productHistoricColumn.minWidthProperty().bind(purchaseTableView.widthProperty().multiply(0.3));
        quantityHistoricColumn.minWidthProperty().bind(purchaseTableView.widthProperty().multiply(0.2));
        createdAtHistoricColumn.minWidthProperty().bind(purchaseTableView.widthProperty().multiply(0.3));

        purchaseObservableList = FXCollections.observableArrayList(this.purchaseRepository.findAll(page));
        purchaseTableView.setItems(purchaseObservableList);
    }



}

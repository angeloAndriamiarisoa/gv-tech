package com.techimage.projectjfx.controller;

import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.controller.table.OrderListActionCellImpl;
import com.techimage.projectjfx.controller.table.ProductSingleActionCellImpl;
import com.techimage.projectjfx.exception.ValidationException;
import com.techimage.projectjfx.model.Order;
import com.techimage.projectjfx.model.OrderTableView;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.OrderRepository;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderController implements Initializable {
    @FXML public TableView<Product> productTableView;
    @FXML public BorderPane containerOrderTableView;
    @FXML public TableView<Order> orderHistoricTableView;
    @FXML public TableView<OrderTableView> orderTableView;

    @FXML TableColumn<OrderTableView, String> productOrderColumn;
    @FXML TableColumn<OrderTableView, String> unitPriceOrderColumn;
    @FXML TableColumn<OrderTableView, String> quantityOrderColumn;
    @FXML TableColumn<OrderTableView, String> totalPriceOrderColumn;
    @FXML TableColumn<OrderTableView, String> idProductOrderColumn;
    @FXML TableColumn<OrderTableView, String> actionOrderColumn;
    @FXML public TableColumn<Product, String> idColumn;
    @FXML public TableColumn<Product, String> nameColumn;
    @FXML public TableColumn<Product, String> unitColumn;
    @FXML public TableColumn<Product, String> unitPriceColumn;
    @FXML public TableColumn<Product, String> actionColumn;

    @FXML public TableColumn<Order, String>idOrderHistoricColumn;
    @FXML public TableColumn<Order, String>customerOrderHistoricColumn;
    @FXML public TableColumn<Order, String>productOrderHistoricColumn;
    @FXML public TableColumn<Order, String>quantityOrderHistoricColumn;
    @FXML public TableColumn<Order, String>priceOrderHistoricColumn;
    @FXML public TableColumn<Order, String>createdAtOrderHistoricColumn;
    @FXML public TextField idProductTxt;
    @FXML
    @TextValidation(label = "Quantité",
            message = "Le quantité doit être un chiffre",
            regex = "^[0-9]+$")
    public TextField quantityTxt;

    @FXML public Label quantityTxtError;
    @FXML public TextField nameTxt;
    @FXML
    public TextField unitTxt;
    @FXML public TextField unitPriceTxt;
    @FXML public Label productTotalPriceTxt;
    @FXML public Label orderTotalPriceTxt;
    @FXML public Button addProduct;
    @FXML public Button removeAllBtn;
    @FXML public Button saveAllBtn;
    @FXML public Pagination pagination;


    @FXML public Tab historicTab;

    private ObservableList<Product> productList;
    private ObservableList<OrderTableView> orderObservableList;
    private ObservableList<Order> orderHistoricObservableList;

    private ArrayList<OrderTableView> orderList;
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private OrderTableView orderTableViewAdded;
    private Product productSelected;
    private OrderController _this = this;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idProductTxt.setVisible(false);
        addProduct.setDisable(true);
        orderList = new ArrayList<>();
        fillProductTable(1);
        fillOrderTable();
        historicTab.setOnSelectionChanged(event -> {
            fillOrderHistoric(1);
            int totalItems = this.orderRepository.countAll();
            com.techimage.projectjfx.controller.pagination.Pagination.setPaginationFactory(
                    pagination,
                    totalItems
            );
            pagination.currentPageIndexProperty().addListener((observableValue, oldValue, newValue) -> {
                fillOrderHistoric((Integer) newValue + 1);
            });
        });
        quantityTxt.setOnKeyTyped(keyEvent -> {
            String qt =  quantityTxt.getText();
            if(quantityTxt.getText().length() == 0) {
                qt = "0";
            }
            ValidationUtil validation = new ValidationUtil(_this);
            try {
                validation.textValidation();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            catch (ValidationException e){
                productTotalPriceTxt.setText("0");
                return;
            }
            Integer quantity = Integer.parseInt(qt);
            Integer unitPrice = Integer.parseInt(unitPriceTxt.getText());
            Integer total = quantity * unitPrice;
            productTotalPriceTxt.setText(total.toString());
        });

        addProduct.setOnMouseClicked(mouseEvent -> {

            ValidationUtil validation = new ValidationUtil(_this);
            try {
                validation.textValidation();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            catch (ValidationException e){
                return;
            }
            Integer quantity = Integer.parseInt(quantityTxt.getText());
            if(productSelected.getQuantity() < quantity) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("");
                alert.setContentText("Le stock pour le produit " + productSelected.getName()
                        + " ("+ productSelected.getQuantity() +
                        ") ne suffit pour le commande demandé("+ quantity+")");
                alert.show();
                return;
            }
            orderTableViewAdded = new OrderTableView(
                    idProductTxt.getText(),
                    nameTxt.getText(),
                    Integer.parseInt(unitPriceTxt.getText()),
                    Integer.parseInt(quantityTxt.getText()),
                    Integer.parseInt( productTotalPriceTxt.getText())
            );

            productSelected.setQuantity(productSelected.getQuantity() - quantity);
            productRepository.update(productSelected, productSelected.getId());
            orderList.add(orderTableViewAdded);
            productTotalPriceTxt.setText("0");
            clearTextField(new TextField []{idProductTxt,nameTxt, unitPriceTxt, unitTxt, quantityTxt});
            fillOrderTable();
            setOrderTotalPriceTxt();
            addProduct.setDisable(true);

        });

        removeAllBtn.setOnMouseClicked(mouseEvent -> {
            orderList.forEach(orderTableView1 -> {
                String id = orderTableView1.getIdProduct();
                Product product = productRepository
                        .findById(id);
                product.setQuantity(product.getQuantity() + orderTableView1.getQuantity());
                productRepository.update(product, product.getId());
            });

            orderList.clear();
            fillOrderTable();
            setOrderTotalPriceTxt();
        });

        saveAllBtn.setOnMouseClicked(mouseEvent -> {
            AtomicInteger i = new AtomicInteger();
            orderTableView.getItems().forEach(orderTable -> {
                Order order = new Order(
                        LocalDateTime.now() +""+ i.getAndIncrement(),
                        orderTable.getIdProduct(),
                        orderTable.getQuantity(),
                        orderTable.getTotalPrice(),
                        "inconnu");
                orderRepository.save(order);
            });
            orderList.clear();
            fillOrderTable();
        });

    }

    private void fillProductTable (Integer page) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalPriceOrderColumn.setCellValueFactory(new  PropertyValueFactory<>("totalPrice"));

        productList = FXCollections.observableArrayList(this.productRepository.findAll(page));
        productTableView.setItems(productList);
        actionColumn.setCellFactory(param -> new ProductSingleActionCellImpl (){
            @Override
            public void add() {
                productTableView.getSelectionModel().select(getIndex());
                productSelected = productTableView.getSelectionModel().getSelectedItem();
                idProductTxt.setText(productSelected.getId());
                nameTxt.setText(productSelected.getName());
                unitTxt.setText(productSelected.getUnit());
                unitPriceTxt.setText(productSelected.getUnitPrice().toString());
                quantityTxt.clear();
                addProduct.setDisable(false);
            }
        });
    }

    private void fillOrderTable () {
        idProductOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        productOrderColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityOrderColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceOrderColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalPriceOrderColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        if (!orderList.isEmpty()) {
            containerOrderTableView.setVisible(true);
        }
        else {
            containerOrderTableView.setVisible(false);
        }
        orderObservableList = FXCollections.observableArrayList(orderList);
        orderTableView.setItems(orderObservableList);
        actionOrderColumn.setCellFactory(param -> new OrderListActionCellImpl(){
            @Override
            public void remove() {
                orderTableView.getSelectionModel().select(getIndex());
                OrderTableView orderTable = orderTableView.getSelectionModel().getSelectedItem();
                String id = orderTable.getIdProduct();
                Product product = productRepository
                        .findById(id);
                product.setQuantity(product.getQuantity() + orderTable.getQuantity());
                productRepository.update(product, product.getId());
                orderList.remove(orderTable);
                if (!orderList.isEmpty()) {
                    containerOrderTableView.setVisible(true);
                }
                else {
                    containerOrderTableView.setVisible(false);
                }
                orderObservableList = FXCollections.observableArrayList(orderList);
                orderTableView.setItems(orderObservableList);
                setOrderTotalPriceTxt();
            }
        });
    }

    private void fillOrderHistoric (Integer page) {
        idOrderHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idProductOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        customerOrderHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        quantityOrderHistoricColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceOrderHistoricColumn.setCellValueFactory(new  PropertyValueFactory<>("totalPrice"));
        createdAtOrderHistoricColumn.setCellValueFactory(new  PropertyValueFactory<>("createdAt"));
        orderHistoricObservableList = FXCollections.observableArrayList(
                orderRepository.findAll(page));
        orderHistoricTableView.setItems(orderHistoricObservableList);
    }


    private void clearTextField (TextField [] arrayTextField) {
        Arrays.stream(arrayTextField).toList().forEach(textField -> textField.clear());
    }

    private void setOrderTotalPriceTxt () {
        AtomicInteger total = new AtomicInteger();
        total.set(0);
        orderList.forEach(order -> {
            total.set(total.get() + order.getTotalPrice().intValue());
        });
        orderTotalPriceTxt.setText(total.toString());
    }

    private void saveAllOrder() {

    }
}

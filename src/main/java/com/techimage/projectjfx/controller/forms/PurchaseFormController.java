package com.techimage.projectjfx.controller.forms;

import com.techimage.projectjfx.EventBtn;
import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.controller.dialog.Dialog;
import com.techimage.projectjfx.controller.toast.Toast;
import com.techimage.projectjfx.exception.ValidationException;
import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.model.Purchase;
import com.techimage.projectjfx.repository.ProductRepository;
import com.techimage.projectjfx.repository.PurchaseRepository;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PurchaseFormController implements Initializable {
        public static Product productToPurchage;

        @FXML
        public Label title;
        @FXML
        public AnchorPane productFormContainer;
        @FXML public TextField nowQuantityTxt;
        public TextField nameTxt;

        public ChoiceBox unitTxt;

        public TextField unitPriceTxt;


        @FXML
        @TextValidation(label = "Quantité",
            message = "La quantité doit être un chiffre",
            regex = "^[0-9]+$")
        public TextField quantityTxt;

        @FXML
        public Button saveBtn;
        @FXML
        public Button resetBtn;
    @FXML
    public Label quantityTxtError;

        private com.techimage.projectjfx.controller.forms.PurchaseFormController _this = this;

        private ProductRepository productRepository = new ProductRepository();
        private PurchaseRepository purchaseRepository = new PurchaseRepository();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            List<String> choice = new ArrayList<>();
            choice.add("Pièce                ");
            choice.add("Carton               ");
            unitTxt.setItems(FXCollections.observableList(choice));
            this.setTextFields();

            EventBtn eventBtn = new EventBtn() {
                @Override
                public void save() {
                    _this.save();
                }

                @Override
                public void reset() {
                    quantityTxt.clear();
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
                Product product = productToPurchage;
                product.setQuantity(product.getQuantity() + Integer.
                        parseInt(quantityTxt.getText().trim()));
                this.productRepository.update(product, productToPurchage.getId());
                productToPurchage = null;
                Purchase purchase = new Purchase();
                purchase.setProduct(product.getName());
                purchase.setQuantity(Integer.parseInt(quantityTxt.getText()));
                purchaseRepository.save(purchase);
                Toast.start("Approvisionnement fini!", Toast.SUCCESS);
                Dialog.dialog.hide();

            }
            catch (ValidationException exception) {
                // throw new RuntimeException(exception);
                System.out.println("erruer sur la validation");
            }
        }

        public void setTextFields () {
               this.title.setText("Approvisionnement : " + productToPurchage.getName());
                nameTxt.setText(productToPurchage.getName());
                nameTxt.setEditable(false);
               unitTxt.getSelectionModel().select(productToPurchage.getUnit());
                unitTxt.setDisable(true);
                unitPriceTxt.setText(productToPurchage.getUnitPrice().toString());
                unitPriceTxt.setEditable(false);
            nowQuantityTxt.setText(productToPurchage.getQuantity().toString());
            nowQuantityTxt.setEditable(false);
                quantityTxt.setText("0");
                quantityTxt.setVisible(true);

        }


}

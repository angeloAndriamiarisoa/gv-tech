package com.techimage.projectjfx.model;


import com.techimage.projectjfx.annotations.entity.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(pk = "id")
public class Order {
    private String id;
    private String idProduct;
    private Integer quantity;
    private Integer totalPrice;
    private String customerName;
    private String createdAt;

    public Order(String id, String idProduct, Integer quantity, Integer totalPrice, String customerName) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public Order() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

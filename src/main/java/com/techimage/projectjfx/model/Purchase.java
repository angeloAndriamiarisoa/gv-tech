package com.techimage.projectjfx.model;

import com.techimage.projectjfx.annotations.entity.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(pk = "id")
public class Purchase {
    private String id;
    private String product;
    private Integer quantity;
    private String createdAt;

    public Purchase(String product, Integer quantity) {
        Long timestamp = System.currentTimeMillis();
        this.id = timestamp.toString();
        this.product = product;
        this.quantity = quantity;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public Purchase() {
        Long timestamp = System.currentTimeMillis();
        this.id = timestamp.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

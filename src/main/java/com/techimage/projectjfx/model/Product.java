package com.techimage.projectjfx.model;


import com.techimage.projectjfx.annotations.entity.Entity;


@Entity(pk = "id")
public class Product {

    private String id;
    private String name;
    private String unit;
    private Integer unitPrice;
    private Integer quantity;
    private Boolean isDeleted;

    public Product() {
        this.isDeleted = false;
        this.quantity = 0;
    };
    public Product(String id, String name, String unit, Integer unitPrice, Integer quantity) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.isDeleted = false;
    }

    public Product(String id, String name, String unit, Integer unitPrice, Integer quantity, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit.toString() + '\'' +
                ", unitPrice='" + unitPrice.toString() + '\'' +
                ", quantity='" + quantity.toString() + '\'' +
                ", isDeleted='" + isDeleted.toString() + '\'' +
                '}';
    }
}

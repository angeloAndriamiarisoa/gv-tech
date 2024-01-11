package com.techimage.projectjfx.model;


import com.techimage.projectjfx.annotations.entity.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(pk = "id")
public class Order {
    private String id;
    private String idProduct;
    private Integer quantity;
    private Integer totalPrice;
    private String customerName;
    private LocalDateTime created_at;
}

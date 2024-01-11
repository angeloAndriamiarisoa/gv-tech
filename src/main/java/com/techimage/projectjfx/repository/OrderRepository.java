package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.model.Order;
import com.techimage.projectjfx.repository.generic.GenericCrudImpl;

public class OrderRepository extends GenericCrudImpl<Order, String> {
    public OrderRepository() {
        super(new Order());
    }
}

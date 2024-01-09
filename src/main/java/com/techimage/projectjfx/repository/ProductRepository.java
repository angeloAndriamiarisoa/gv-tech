package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.model.Product;
import com.techimage.projectjfx.repository.generic.GenericCrudImpl;

public class ProductRepository extends GenericCrudImpl<Product, String> {
    public ProductRepository() {
        super(new Product());
    }
}

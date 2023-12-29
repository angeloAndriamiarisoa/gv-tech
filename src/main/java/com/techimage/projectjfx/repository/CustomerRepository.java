package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.model.Customer;

public class CustomerRepository extends GenericCrudImpl<Customer, String> {
    public CustomerRepository () {
        super(new Customer());
    }
}

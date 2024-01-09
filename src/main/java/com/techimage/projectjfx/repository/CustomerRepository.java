package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.model.Customer;
import com.techimage.projectjfx.repository.generic.GenericCrudImpl;

public class CustomerRepository extends GenericCrudImpl<Customer, String> {
    public CustomerRepository () {
        super(new Customer());
    }
}

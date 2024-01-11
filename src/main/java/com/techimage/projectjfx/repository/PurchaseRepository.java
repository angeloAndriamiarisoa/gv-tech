package com.techimage.projectjfx.repository;

import com.techimage.projectjfx.model.Purchase;
import com.techimage.projectjfx.repository.generic.GenericCrudImpl;

public class PurchaseRepository  extends GenericCrudImpl<Purchase, String> {
    public PurchaseRepository() {
        super(new Purchase());
    }
}

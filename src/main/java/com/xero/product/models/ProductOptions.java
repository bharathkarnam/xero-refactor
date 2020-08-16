package com.xero.product.models;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductOptions {
    @NonNull
    private String productId;
    @Id
    private String optionsId;
    private String name;
    private String description ;

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }




    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}

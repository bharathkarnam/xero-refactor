package com.xero.product.testutil;

import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;

import java.util.*;

public final class Util {
    public static Map<String, List<Product>> getproductmap(){
        Map<String, List<Product>> productmap = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        productList.add(genProduct());
        productmap.put("items",productList);
        return productmap;
    }

    public static Product genProduct(){
        Product product = new Product();
        product.setDeliveryPrice(10.20);
        product.setDescription("Test");
        product.setName("Test");
        product.setPrice(20.20);
        product.setProductId("Test");
        return product;
    }

    public static ProductOptions genProductOptions(){
        ProductOptions productOptions = new ProductOptions();
        productOptions.setOptionsId("Test");
        productOptions.setProductId("Test");
        productOptions.setDescription("Test");
        productOptions.setName("Test");
        return productOptions;
    }

    public static Map<String, List<ProductOptions>> getproductoptionsmap(){
        Map<String, List<ProductOptions>> productoptionsmap = new HashMap<>();
        List<ProductOptions> productOptionsListList = new ArrayList<>();
        productOptionsListList.add(genProductOptions());
        productoptionsmap.put("items",productOptionsListList);
        return productoptionsmap;
    }
}

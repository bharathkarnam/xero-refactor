package com.xero.product.services;

import com.xero.product.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Map<String, List<Product>> getAllProducts();

    Product insertProduct(Product product);

    Product updateProduct(Product product);

    Product getProductByID(String productId);

    Product deleteProductByID(String productId);
}

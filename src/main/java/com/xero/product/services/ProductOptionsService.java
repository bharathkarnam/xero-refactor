package com.xero.product.services;

import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;

import java.util.List;
import java.util.Map;

public interface ProductOptionsService {
    Map<String, List<ProductOptions>> getProductsOptionsByProductID(String productId) throws Exception;

    ProductOptions insertProductOptions(ProductOptions productOptions) throws Exception;

    ProductOptions updateByProductIdAndOptionsId(ProductOptions product) throws Exception;

    ProductOptions getProductOptonsByProductIDAndByOptionsID(String productId,String optionsId) throws Exception;

    ProductOptions deleteProductByProductIDAndOptionsID(String productId,String optionsId) throws Exception;
}

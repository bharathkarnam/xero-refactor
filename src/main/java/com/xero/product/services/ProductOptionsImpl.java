package com.xero.product.services;

import com.xero.product.daoimpl.ProductDaoService;
import com.xero.product.daoimpl.ProductOptionDaoService;
import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductOptionsImpl implements ProductOptionsService {
    @Autowired
    ProductOptionDaoService productOptionDaoService;
    private final Logger logger = LoggerFactory.getLogger(ProductOptionsImpl.class);


    @Override
    public Map<String, List<ProductOptions>> getProductsOptionsByProductID (String productId) throws Exception {
        Map<String, List<ProductOptions>> mp= new HashMap<>();
        mp.put("items", productOptionDaoService.findByProductID(productId));
        return mp;
    }

    @Override
    public ProductOptions insertProductOptions(ProductOptions productOptions) throws Exception {
        return  productOptionDaoService.insertOption(productOptions);
    }

    @Override
    public ProductOptions updateByProductIdAndOptionsId(ProductOptions productOptions) throws Exception {
        return productOptionDaoService.updateProductOptions(productOptions);
    }

    @Override
    public ProductOptions getProductOptonsByProductIDAndByOptionsID(String productId, String optionsId) throws Exception {
        return productOptionDaoService.findProductOptionsByProductIdAndByOptionId(productId, optionsId);
    }

    @Override
    public ProductOptions deleteProductByProductIDAndOptionsID(String productId, String optionsId) throws Exception {
        return productOptionDaoService.deleteByProductIDAndOptionsID(productId,optionsId);
    }
}

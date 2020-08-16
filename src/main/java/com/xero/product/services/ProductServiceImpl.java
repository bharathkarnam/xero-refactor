package com.xero.product.services;

import com.xero.product.daoimpl.ProductDaoService;
import com.xero.product.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDaoService productDaoService;
    private final Logger logger = LoggerFactory.getLogger(ProductOptionsImpl.class);



    @Override
    public Map<String, List<Product>> getAllProducts()  {
        try {
            Map<String, List<Product>> mp = new HashMap<>();
            mp.put("items", productDaoService.getAllProducts());
            return mp;
        } catch(Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Product insertProduct(Product product) {
      return  productDaoService.insertProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDaoService.updateProduct(product);
    }

    @Override
    public Product getProductByID(String productId) {
        return productDaoService.findByProductID(productId);
    }

    @Override
    public Product deleteProductByID(String productId) {
         return productDaoService.deleteByProductID(productId);
    }
}

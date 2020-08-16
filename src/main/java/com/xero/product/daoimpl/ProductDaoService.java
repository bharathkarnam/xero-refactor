package com.xero.product.daoimpl;

import com.xero.product.controller.ProductController;
import com.xero.product.dao.ProductDao;
import com.xero.product.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductDaoService {

    @Autowired
    ProductDao productDao;

    private final Logger logger = LoggerFactory.getLogger(ProductDaoService.class);


    public List<Product> getAllProducts() {
        try {
            final List<Product> products= new ArrayList<>();
            productDao.findAll().forEach(product -> products.add(product));
            return products;
        } catch(Exception e){
            logger.error(e.getMessage());
           throw e;
        }

    }

    public Product insertProduct(Product product)  {
        try {
            if(product.getProductId().isBlank())
            {product.setProductId(UUID.randomUUID().toString());}
            return productDao.save(product);
        } catch(Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    public Product updateProduct(Product product) {
        try {
            return productDao.save(product);
        } catch(Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    public Product findByProductID(String productID) {
        try {
            return productDao.findByProductId(productID);
        } catch(Exception e){
            logger.error(e.getMessage()+":"+productID);
            throw e;
        }
    }

    public Product deleteByProductID(String productID) {
        try {
            Product product = findByProductID(productID);
            productDao.delete(product);
            return product;
        } catch(Exception e){
            logger.error(e.getMessage()+":"+productID);
            throw e;
        }
    }
}

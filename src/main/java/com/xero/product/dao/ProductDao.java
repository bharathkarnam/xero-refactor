package com.xero.product.dao;

import com.xero.product.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

    Product findByProductId(String productId);

}

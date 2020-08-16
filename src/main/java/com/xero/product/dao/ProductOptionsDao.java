package com.xero.product.dao;

import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionsDao extends CrudRepository<ProductOptions, String> {
    List<ProductOptions> findByProductId(String productID);


    ProductOptions findByProductIdAndOptionsId(String productId,String optionsId);

}

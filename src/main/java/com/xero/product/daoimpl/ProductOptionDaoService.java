package com.xero.product.daoimpl;

import com.xero.product.dao.ProductOptionsDao;
import com.xero.product.models.ProductOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductOptionDaoService {

    @Autowired
    ProductOptionsDao productOptionsDao;

    public List<ProductOptions> findByProductID(String productID) {
        try {
            final List<ProductOptions> productOptions= new ArrayList<>();
            productOptionsDao.findByProductId(productID).forEach(productOption -> productOptions.add(productOption));
            return productOptions;
        } catch(Exception e){
            throw e;
        }

    }


    public ProductOptions findProductOptionsByProductIdAndByOptionId(String productId,String optionsId){
        try {
            return productOptionsDao.findByProductIdAndOptionsId(productId, optionsId);
        } catch(Exception e){
            throw e;
        }
    }

    public ProductOptions insertOption(ProductOptions productOptions) {
        try {
            if(productOptions.getOptionsId().isBlank())
            {productOptions.setOptionsId(UUID.randomUUID().toString());}
            return productOptionsDao.save(productOptions);
        }catch(Exception e){
            throw e;
        }
    }

    public ProductOptions updateProductOptions(ProductOptions productOptions) {
        try {
            return productOptionsDao.save(productOptions);
        } catch(Exception e){
            throw e;
        }
    }

    public ProductOptions deleteByProductIDAndOptionsID(String productID,String optionsID) {
        try {
            ProductOptions productOptions = findProductOptionsByProductIdAndByOptionId(productID, optionsID);
            productOptionsDao.delete(productOptions);
            return productOptions;
        }catch(Exception e){
            throw e;
        }
    }
}

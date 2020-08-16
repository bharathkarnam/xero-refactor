package com.xero.product.controller;

import com.xero.product.models.Product;
import com.xero.product.services.ProductService;
import com.xero.product.util.Constants;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(tags = Constants.SWAGGER_TAGS, value = Constants.SWAGGER_VALUE)
@RequestMapping("/api/")
public class ProductController {
    @Autowired
    ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(Constants.PRODUCTS)
    public  ResponseEntity<Map<String, List<Product>>> getProducts() {
        try {
                logger.info(this.getClass()+":");
                return new ResponseEntity<Map<String, List<Product>>>(productService.getAllProducts(), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new  ResponseEntity<Map<String, List<Product>>>((Map<String, List<Product>>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(Constants.PRODUCTS_BY_ID)
    public ResponseEntity<Product> getProductById(@RequestParam @NonNull String productId) {
        try {
            logger.info(this.getClass()+":");
            return new ResponseEntity<Product>(productService.getProductByID(productId), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<Product>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @PostMapping(Constants.PRODUCTS)
    public ResponseEntity<Product> insertProducts(@RequestBody @NonNull Product product) {
        try {

            logger.info(this.getClass()+":");
            return new ResponseEntity<Product>(productService.insertProduct(product), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(Constants.PRODUCTS)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            if(!product.getProductId().isBlank()) {
                logger.info(this.getClass() + ":");
                return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Product>(product,
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<Product>(product,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(Constants.PRODUCTS_BY_ID)
    public ResponseEntity<Product> deleteProductById(@RequestParam @NonNull String  productId){
        try {
            logger.info(this.getClass()+":");
            return new ResponseEntity<Product>( productService.deleteProductByID(productId), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(this.getClass()+":"+e.getMessage()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<Product>(new Product(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

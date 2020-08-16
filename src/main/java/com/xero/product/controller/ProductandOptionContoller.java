package com.xero.product.controller;

import com.google.gson.Gson;
import com.xero.product.models.ProductOptions;
import com.xero.product.services.ProductOptionsService;
import com.xero.product.util.Constants;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = Constants.SWAGGER_TAGS, value = Constants.SWAGGER_VALUE)
@RequestMapping("/api/"+Constants.PRODUCTS+"/")
public class ProductandOptionContoller {

    @Autowired
    ProductOptionsService productOptionsService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    Gson responseJson = new Gson();

    @GetMapping(Constants.OPTIONS)
    public ResponseEntity<Map<String, List<ProductOptions>>> getProductOptions(@RequestParam @NonNull String productId) {
        try {
            logger.info(this.getClass()+":");
            return new ResponseEntity<Map<String, List<ProductOptions>>>(productOptionsService.getProductsOptionsByProductID(productId), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<Map<String, List<ProductOptions>>>((Map<String, List<ProductOptions>>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(Constants.PRODUCTS_ID_OPTIONS_ID)
    public ResponseEntity<ProductOptions> getProductOptionsByProductIdAndByOptionId(@RequestParam @NonNull String productId,@RequestParam @NonNull String optionsId) {
        try {
            logger.info(this.getClass()+":");
            return new ResponseEntity<ProductOptions>(productOptionsService.getProductOptonsByProductIDAndByOptionsID(productId,optionsId), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<ProductOptions>(new ProductOptions(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(Constants.OPTIONS)
    public ResponseEntity<ProductOptions> insertProductOptions(@RequestBody @NonNull ProductOptions productOptions) {
        try {
            logger.info(this.getClass()+":");
            if(!productOptions.getProductId().isBlank()) {
                return new ResponseEntity<ProductOptions>(productOptionsService.insertProductOptions(productOptions), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProductOptions>(productOptions, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<ProductOptions>(productOptions, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(Constants.OPTIONS)
    public ResponseEntity<ProductOptions> updateProductOptionsByProductIdAndByOptionId(@RequestBody ProductOptions productOptions) {
        try {
            if(null != productOptions.getProductId() && null != productOptions.getOptionsId()) {
                logger.info(this.getClass() + ":");
                return new ResponseEntity<ProductOptions>(productOptionsService.updateByProductIdAndOptionsId(productOptions), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProductOptions>(productOptions, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<ProductOptions>((ProductOptions) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(Constants.PRODUCTS_ID_OPTIONS_ID)
    public ResponseEntity<ProductOptions> deleteProductOptionsByProductIdAndByOptionId(@RequestParam @NonNull String productId,@RequestParam @NonNull String optionsId) {
        try {
            logger.info(this.getClass()+":");
            return new ResponseEntity<ProductOptions>(productOptionsService.deleteProductByProductIDAndOptionsID(productId,optionsId), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error(this.getClass()+":"+Constants.SOMETHING_IS_NOT_RIGHT+":"+e.getStackTrace());
            return new ResponseEntity<ProductOptions>((ProductOptions) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

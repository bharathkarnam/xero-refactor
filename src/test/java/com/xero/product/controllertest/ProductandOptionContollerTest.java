package com.xero.product.controllertest;

import com.xero.product.controller.ProductandOptionContoller;
import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;
import com.xero.product.services.ProductOptionsImpl;
import com.xero.product.testutil.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductandOptionContollerTest {
    @InjectMocks
    ProductandOptionContoller productandOptionContoller;

    @Mock
    ProductOptionsImpl productOptionsService;


    @Test
    public void getProductOptionsTest() throws Exception {
        when(productOptionsService.getProductsOptionsByProductID("Test")).thenReturn(Util.getproductoptionsmap());
        ResponseEntity<Map<String, List<ProductOptions>>> rs = productandOptionContoller.getProductOptions("Test");
        assertThat(rs.getBody().get("items").get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void getProductOptionsByProductIdAndByOptionIdTest() throws Exception {
        when(productOptionsService.getProductOptonsByProductIDAndByOptionsID("Test","Test")).thenReturn(Util.genProductOptions());
        ResponseEntity<ProductOptions> rs = productandOptionContoller.getProductOptionsByProductIdAndByOptionId("Test","Test");
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void insertProductOptionsTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionsService.insertProductOptions(productOptions)).thenReturn(productOptions);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.insertProductOptions(productOptions);
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void updateProductOptionsByProductIdAndByOptionIdTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setName("YOLO");
        when(productOptionsService.updateByProductIdAndOptionsId(productOptions)).thenReturn(productOptions);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.updateProductOptionsByProductIdAndByOptionId(productOptions);
        assertThat(rs.getBody().getName().equalsIgnoreCase("YOLO"));
    }

    @Test
    public void deleteProductOptionsByProductIdAndByOptionIdTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionsService.deleteProductByProductIDAndOptionsID("Test","Test")).thenReturn(productOptions);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.deleteProductOptionsByProductIdAndByOptionId("Test","Test");
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void getProductOptionsExceptionTest() throws Exception {
        when(productOptionsService.getProductsOptionsByProductID("Test")).thenThrow(new RuntimeException());
        ResponseEntity<Map<String, List<ProductOptions>>> rs = productandOptionContoller.getProductOptions("Test");
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void getProductOptionsByProductIdAndByOptionIdExceptionTest() throws Exception {
        when(productOptionsService.getProductOptonsByProductIDAndByOptionsID("Test","Test")).thenThrow(new RuntimeException());
        ResponseEntity<ProductOptions> rs = productandOptionContoller.getProductOptionsByProductIdAndByOptionId("Test","Test");
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void insertProductOptionsExceptionTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionsService.insertProductOptions(productOptions)).thenThrow(new RuntimeException());
        ResponseEntity<ProductOptions> rs = productandOptionContoller.insertProductOptions(productOptions);
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void updateProductOptionsByProductIdAndByOptionIdExceptionTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setName("YOLO");
        when(productOptionsService.updateByProductIdAndOptionsId(productOptions)).thenThrow(new RuntimeException());
        ResponseEntity<ProductOptions> rs = productandOptionContoller.updateProductOptionsByProductIdAndByOptionId(productOptions);
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void deleteProductOptionsByProductIdAndByOptionIdExceptionTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionsService.deleteProductByProductIDAndOptionsID("Test","Test")).thenThrow(new RuntimeException());
        ResponseEntity<ProductOptions> rs = productandOptionContoller.deleteProductOptionsByProductIdAndByOptionId("Test","Test");
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void updateProductOptionsByProductIdAndByOptionIdNullProductIDTest() {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setProductId(null);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.updateProductOptionsByProductIdAndByOptionId(productOptions);
        assertThat(rs.getStatusCode().is4xxClientError());
    }

    @Test
    public void updateProductOptionsByProductIdAndByOptionIdNullOptionsIDTest() {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setOptionsId(null);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.updateProductOptionsByProductIdAndByOptionId(productOptions);
        assertThat(rs.getStatusCode().is4xxClientError());
    }

    @Test
    public void insertProductOptionsNullProductIDTest() {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setProductId(null);
        ResponseEntity<ProductOptions> rs = productandOptionContoller.insertProductOptions(productOptions);
        assertThat(rs.getStatusCode().is4xxClientError());
    }
}

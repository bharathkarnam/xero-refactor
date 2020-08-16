package com.xero.product.controllertest;

import com.xero.product.controller.ProductController;
import com.xero.product.models.Product;
import com.xero.product.services.ProductServiceImpl;
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
public class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Test
    public void getProductsTest() throws Exception {
    when(productService.getAllProducts()).thenReturn(Util.getproductmap());
    ResponseEntity<Map<String, List<Product>>> rs = productController.getProducts();
    assertThat(rs.getBody().get("items").get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        when(productService.getProductByID("Test")).thenReturn(Util.genProduct());
        ResponseEntity<Product> rs = productController.getProductById("Test");
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }
    @Test
    public void insertproductTest() throws Exception {
        Product product = Util.genProduct();
        when(productService.insertProduct(product)).thenReturn(product);
        ResponseEntity<Product> rs = productController.insertProducts(product);
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void updateProductsTest() throws Exception {
        Product updatedProduct = Util.genProduct();
        updatedProduct.setPrice(40.5);
        when(productService.updateProduct(updatedProduct)).thenReturn(updatedProduct);
        ResponseEntity<Product> rs = productController.updateProduct(updatedProduct);
        assertThat(rs.getBody().getPrice() == 40.5);
    }

    @Test
    public void deleteProductsTest() throws Exception {
        when(productService.deleteProductByID("Test")).thenReturn(Util.genProduct());
        ResponseEntity<Product> rs = productController.deleteProductById("Test");
        assertThat(rs.getBody().getName().equalsIgnoreCase("Test"));
    }



    @Test
    public void getProductsExceptionTest() throws Exception {
        when(productService.getAllProducts()).thenThrow(new RuntimeException());
        ResponseEntity<Map<String, List<Product>>> rs = productController.getProducts();
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void getProductByIdExceptionTest() throws Exception {
        when(productService.getProductByID("Test")).thenThrow(new RuntimeException());
        ResponseEntity<Product> rs = productController.getProductById("Test");
        assertThat(rs.getStatusCode().is5xxServerError());
    }
    @Test
    public void insertproductExceptionTest() throws Exception {
        Product product = Util.genProduct();
        when(productService.insertProduct(product)).thenThrow(new RuntimeException());
        ResponseEntity<Product> rs = productController.insertProducts(product);
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void updateProductsExceptionTest() throws Exception {
        Product updatedProduct = Util.genProduct();
        updatedProduct.setPrice(40.5);
        when(productService.updateProduct(updatedProduct)).thenThrow(new RuntimeException());
        ResponseEntity<Product> rs = productController.updateProduct(updatedProduct);
        assertThat(rs.getStatusCode().is5xxServerError());
    }

    @Test
    public void updateProductsProductIDNullTest() {
        Product updatedProduct = Util.genProduct();
        updatedProduct.setProductId(null);
        ResponseEntity<Product> rs = productController.updateProduct(updatedProduct);
        assertThat(rs.getStatusCode().is4xxClientError());
    }

    @Test
    public void deleteProductsExceptionTest() throws Exception {
        when(productService.deleteProductByID("Test")).thenThrow(new RuntimeException());
        ResponseEntity<Product> rs = productController.deleteProductById("Test");
        assertThat(rs.getStatusCode().is5xxServerError());
    }

}

package com.xero.product.servicetest;

import com.xero.product.daoimpl.ProductDaoService;
import com.xero.product.models.Product;
import com.xero.product.services.ProductServiceImpl;
import com.xero.product.testutil.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductDaoService productDaoService;


    @Test
    public void getAllProductsTest() throws Exception {
        when(productDaoService.getAllProducts()).thenReturn(Util.getproductmap().get("items"));
        Map<String, List<Product>> pr =productService.getAllProducts();
        assertThat(pr.get("items").get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void getProductByIDTest() throws Exception {
        when(productDaoService.findByProductID("Test")).thenReturn(Util.genProduct());
        Product pr =productService.getProductByID("Test");
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void insertProductTest() throws Exception {
        Product product = Util.genProduct();
        when(productDaoService.insertProduct(product)).thenReturn(product);
        Product pr =productService.insertProduct(product);
        assertThat(product.getDeliveryPrice() == 10.20);
        assertThat(product.getDescription().equalsIgnoreCase("Test"));
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void updateProductTest()  {
        Product product = Util.genProduct();
        product.setPrice(55);
        when(productDaoService.updateProduct(product)).thenReturn(product);
        Product pr =productService.updateProduct(product);
        assertThat(pr.getPrice() == 55);
    }

    @Test
    public void deleteProductTest() {
        when(productDaoService.deleteByProductID("Test")).thenReturn(Util.genProduct());
        Product pr =productService.deleteProductByID("Test");
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test(expected = Exception.class)
    public void getAllProductsExceptionTest()  {
        when(productDaoService.getAllProducts()).thenThrow(new RuntimeException());
        Map<String, List<Product>> pr =productService.getAllProducts();
    }

    @Test(expected = Exception.class)
    public void getProductByIDExceptionTest() {
        when(productDaoService.findByProductID("Test")).thenThrow(new RuntimeException());
        productService.getProductByID("Test");
    }

    @Test(expected = Exception.class)
    public void insertProductExceptionTest()  {
        Product product = Util.genProduct();
        when(productDaoService.insertProduct(product)).thenThrow(new RuntimeException());
        productService.insertProduct(product);
    }

    @Test(expected = Exception.class)
    public void updateProductExceptionTest() {
        Product product = Util.genProduct();
        product.setPrice(55);
        when(productDaoService.updateProduct(product)).thenThrow(new RuntimeException());
        productService.updateProduct(product);
    }

    @Test(expected = Exception.class)
    public void deleteProductExceptionTest()  {
        when(productDaoService.deleteByProductID("Test")).thenThrow(new RuntimeException());
        productService.deleteProductByID("Test");
    }
}

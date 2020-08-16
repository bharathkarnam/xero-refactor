package com.xero.product.servicetest;

import com.xero.product.daoimpl.ProductOptionDaoService;
import com.xero.product.models.ProductOptions;
import com.xero.product.services.ProductOptionsImpl;
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
public class ProductOptionsServiceTest {
    @InjectMocks
    ProductOptionsImpl productOptionsService;

    @Mock
    ProductOptionDaoService productOptionDaoService;


    @Test
    public void getProductsOptionsByProductIDTest() throws Exception {
        when(productOptionDaoService.findByProductID("Test")).thenReturn(Util.getproductoptionsmap().get("items"));
        Map<String, List<ProductOptions>> pr = productOptionsService.getProductsOptionsByProductID("Test");
        assertThat(pr.get("items").get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void getProductOptonsByProductIDAndByOptionsIDTest() throws Exception {
        when(productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test")).thenReturn(Util.genProductOptions());
        ProductOptions productOptions =productOptionsService.getProductOptonsByProductIDAndByOptionsID("Test","Test");
        assertThat(productOptions.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void insertProductOptionsTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionDaoService.insertOption(productOptions)).thenReturn(productOptions);
        ProductOptions pr =productOptionsService.insertProductOptions(productOptions);
        assertThat(pr.getName().equalsIgnoreCase("Test"));
        assertThat(pr.getDescription().equalsIgnoreCase("Test"));

    }

    @Test
    public void updateByProductIdAndOptionsIdTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setName("Test1");
        when(productOptionDaoService.updateProductOptions(productOptions)).thenReturn(productOptions);
        ProductOptions pr =productOptionsService.updateByProductIdAndOptionsId(productOptions);
        assertThat(pr.getName().equalsIgnoreCase("Test1"));
    }

    @Test
    public void deleteProductByProductIDAndOptionsIDTest() throws Exception {
        when(productOptionDaoService.deleteByProductIDAndOptionsID("Test","Test")).thenReturn(Util.genProductOptions());
        ProductOptions pr =productOptionsService.deleteProductByProductIDAndOptionsID("Test","Test");
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test(expected = Exception.class)
    public void getProductsOptionsByProductIDExceptionTest() throws Exception {
        when(productOptionDaoService.findByProductID("Test")).thenThrow(new RuntimeException());
        Map<String, List<ProductOptions>> pr =productOptionsService.getProductsOptionsByProductID("Test");
    }

    @Test(expected = Exception.class)
    public void getProductOptonsByProductIDAndByOptionsIDExceptionTest() throws Exception {
        when(productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test")).thenThrow(new RuntimeException());
        productOptionsService.getProductOptonsByProductIDAndByOptionsID("Test","Test");
    }

    @Test(expected = Exception.class)
    public void insertProductExceptionTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        when(productOptionDaoService.insertOption(productOptions)).thenThrow(new RuntimeException());
        productOptionsService.insertProductOptions(productOptions);
    }

    @Test(expected = Exception.class)
    public void updateProductExceptionTest() throws Exception {
        ProductOptions productOptions = Util.genProductOptions();
        productOptions.setName("Test");
        when(productOptionDaoService.updateProductOptions(productOptions)).thenThrow(new RuntimeException());
        productOptionsService.updateByProductIdAndOptionsId(productOptions);
    }

    @Test(expected = Exception.class)
    public void deleteProductExceptionTest() throws Exception {
        when(productOptionDaoService.deleteByProductIDAndOptionsID("Test","Test")).thenThrow(new RuntimeException());
        productOptionsService.deleteProductByProductIDAndOptionsID("Test","Test");
    }
}

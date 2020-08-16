package com.xero.product.daoservicetest;

import com.xero.product.dao.ProductDao;
import com.xero.product.daoimpl.ProductDaoService;
import com.xero.product.models.Product;
import com.xero.product.testutil.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductDaoImplTest {
    @InjectMocks
    ProductDaoService productDaoService;

    @Mock
    ProductDao productDao;

    Product product = Util.genProduct();

    @Test
    public void getAllProductsdaoTest() {
        when(productDao.findAll()).thenReturn(Util.getproductmap().get("items"));
        List<Product> pr = productDaoService.getAllProducts();
        assertThat(pr.get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void findByProductIDdaoTest() {
        when(productDao.findByProductId("Test")).thenReturn(product);
        Product pr = productDaoService.findByProductID("Test");
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void insertProductdaoTest() {
        when(productDao.save(product)).thenReturn(product);
        Product pr = productDaoService.insertProduct(product);
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void updateProductdaoTest() {
        Product pr = Util.genProduct();
        pr.setPrice(1);
        when(productDao.save(pr)).thenReturn(pr);
        Product result = productDaoService.updateProduct(pr);
        assertThat(pr.getPrice() == 1);
    }

    @Test
    public void deleteProductdaoTest() {
        when(productDaoService.findByProductID("Test")).thenReturn(product);
        Product pr = productDaoService.deleteByProductID("Test");
        verify(productDao, times(1)).delete(eq(product));
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test(expected = Exception.class)
    public void getAllProductsdaoExceptionTest() {
        when(productDao.findAll()).thenThrow(new RuntimeException());
        productDaoService.getAllProducts();
    }

    @Test(expected = Exception.class)
    public void findByProductIDdaoExceptionTest() {
        when(productDao.findByProductId("Test")).thenThrow(new RuntimeException());
        productDaoService.findByProductID("Test");
    }

    @Test(expected = Exception.class)
    public void insertProductdaoExceptionTest() {
        when(productDao.save(product)).thenThrow(new RuntimeException());
        productDaoService.insertProduct(product);
    }

    @Test(expected = Exception.class)
    public void updateProductdaoExceptionTest() {
        Product pr = Util.genProduct();
        pr.setPrice(1);
        when(productDao.save(pr)).thenThrow(new RuntimeException());
        productDaoService.updateProduct(pr);
    }

    @Test(expected = Exception.class)
    public void deleteProductdaoExceptionTest() {
        when(productDaoService.findByProductID("Test")).thenThrow(new RuntimeException());
        Product pr = productDaoService.deleteByProductID("Test");
    }
}

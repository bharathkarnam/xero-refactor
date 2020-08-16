package com.xero.product.daoservicetest;

import com.xero.product.dao.ProductOptionsDao;
import com.xero.product.daoimpl.ProductOptionDaoService;
import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;
import com.xero.product.testutil.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductOptionsDaoImplTest {
    @InjectMocks
    ProductOptionDaoService productOptionDaoService;

    @Mock
    ProductOptionsDao productOptionsDao;

    ProductOptions productOptions = Util.genProductOptions();

    @Test
    public void findByProductIDdaoTest(){
        when(productOptionsDao.findByProductId("Test")).thenReturn(Util.getproductoptionsmap().get("items"));
        List<ProductOptions> pr = productOptionDaoService.findByProductID("Test");
        assertThat(pr.get(0).getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void findProductOptionsByProductIdAndByOptionIddaoTest(){
        when(productOptionsDao.findByProductIdAndOptionsId("Test","Test")).thenReturn(productOptions);
        ProductOptions pr = productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test");
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void insertOptiondaoTest(){
        when(productOptionsDao.save(productOptions)).thenReturn(productOptions);
        ProductOptions pr = productOptionDaoService.insertOption(productOptions);
        assertThat(pr.getName().equalsIgnoreCase("Test"));
    }

    @Test
    public void updateProductOptionsDaoTest(){
        ProductOptions productOptionsUpdate = Util.genProductOptions();
        productOptionsUpdate.setName("Update");
        when(productOptionsDao.save(productOptionsUpdate)).thenReturn(productOptionsUpdate);
        ProductOptions pr = productOptionDaoService.updateProductOptions(productOptionsUpdate);
        assertThat(pr.getName().equalsIgnoreCase("Update"));
    }

    @Test
    public void deleteByProductIDAndOptionsIDDaoTest(){
        when(productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test")).thenReturn(productOptions);
        ProductOptions pr = productOptionDaoService.deleteByProductIDAndOptionsID("Test","Test");
        verify(productOptionsDao, times(1)).delete(eq(productOptions));
        assertThat(pr.getName().equalsIgnoreCase("Update"));
    }


    @Test(expected = Exception.class)
    public void findByProductIDdaoExceptionTest(){
        when(productOptionsDao.findByProductId("Test")).thenThrow(new RuntimeException());
        productOptionDaoService.findByProductID("Test");

    }

    @Test(expected = Exception.class)
    public void findProductOptionsByProductIdAndByOptionIdDaoExceptionTest(){
        when(productOptionsDao.findByProductIdAndOptionsId("Test","Test")).thenThrow(new RuntimeException());
        productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test");
    }

    @Test(expected = Exception.class)
    public void insertOptionDaoExceptionTest(){
        when(productOptionsDao.save(productOptions)).thenThrow(new RuntimeException());
        productOptionDaoService.insertOption(productOptions);
    }

    @Test(expected = Exception.class)
    public void updateProductOptionsDaoExceptionTest(){
        ProductOptions productOptionsUpdate = Util.genProductOptions();
        productOptionsUpdate.setName("Update");
        when(productOptionsDao.save(productOptionsUpdate)).thenThrow(new RuntimeException());
        productOptionDaoService.updateProductOptions(productOptionsUpdate);
    }

    @Test(expected = Exception.class)
    public void deleteByProductIDAndOptionsIDDaoExceptionTest(){
        when(productOptionDaoService.findProductOptionsByProductIdAndByOptionId("Test","Test")).thenThrow(new RuntimeException());
        productOptionDaoService.deleteByProductIDAndOptionsID("Test","Test");

    }
}

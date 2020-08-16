package com.xero.product.modeltest;

import com.xero.product.models.Product;
import com.xero.product.testutil.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProductModelTest {

    @Test
    public void testProductModel(){
        Product product = Util.genProduct();

    }
}

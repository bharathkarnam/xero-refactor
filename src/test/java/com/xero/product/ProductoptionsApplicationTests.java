package com.xero.product;

import com.xero.product.controller.ProductController;
import com.xero.product.controller.ProductandOptionContoller;
import com.xero.product.controllertest.ProductControllerTest;
import com.xero.product.controllertest.ProductandOptionContollerTest;
import com.xero.product.daoservicetest.ProductDaoImplTest;
import com.xero.product.daoservicetest.ProductOptionsDaoImplTest;
import com.xero.product.servicetest.ProductOptionsServiceTest;
import com.xero.product.servicetest.ProductServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@RunWith(Suite.class)
@ActiveProfiles("test")
@Suite.SuiteClasses({ProductControllerTest.class, ProductandOptionContollerTest.class, ProductOptionsServiceTest.class, ProductServiceTest.class, ProductDaoImplTest.class, ProductOptionsDaoImplTest.class})
class ProductoptionsApplicationTests {
}

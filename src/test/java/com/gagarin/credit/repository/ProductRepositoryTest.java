package com.gagarin.credit.repository;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.model.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testProductEntityRepository() {
        Long code = 1010L;
        ProductEntity productEntity = new ProductEntity(3000, 30000, 5, 24, 6);
        productEntity.setCode(code);
        productRepository.save(productEntity);

        ProductEntity productEntity1 = productRepository.findAll().get(0);
    }
}

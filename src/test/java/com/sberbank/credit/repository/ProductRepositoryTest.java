package com.sberbank.credit.repository;

import com.sberbank.credit.config.AppConfig;
import com.sberbank.credit.model.ProductEntity;
import com.sberbank.credit.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public void testFindBySum(){
        double sum = 5000.00;

        ProductEntity preparedEntity = new ProductEntity(3000, 30000, 5,24,6);
        preparedEntity.setCode(1006L);

        productRepository.save(preparedEntity);

        ProductEntity entity =  productRepository.findOne(Specification.where(
                ProductServiceImpl.sumBetweenSpec((int) Math.round(sum))
        )).orElse(null);

        Assert.assertNotNull("can not be null", entity);
    }
}

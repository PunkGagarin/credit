package com.sberbank.credit.test.repository;

import com.sberbank.credit.config.AppConfig;
import com.sberbank.credit.model.entities.ProductEntity;
import com.sberbank.credit.repository.ProductRepository;
import com.sberbank.credit.service.product.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindBySum(){
        double sum = 300005.00;

        ProductEntity preparedEntity = new ProductEntity(300000, 300010, 5,24,6);
        preparedEntity.setCode(1006L);

        productRepository.save(preparedEntity);

        ProductEntity entity =  productRepository.findOne(Specification.where(
                ProductServiceImpl.sumBetweenSpec((int) Math.round(sum))
        )).orElse(null);

        Assert.assertNotNull("can not be null", entity);

        productRepository.delete(preparedEntity);
    }
}

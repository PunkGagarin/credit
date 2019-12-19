package com.gagarin.credit.repository;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, SecurityConfig.class})
public class CreditRequestRepositoryTest {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    @Test
    public void testCreditRequestRepositoryTest(){

    }
}

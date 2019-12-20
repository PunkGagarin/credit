package com.sberbank.credit.repository;

import com.sberbank.credit.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRepo(){
        userRepository.findAll();
    }
}

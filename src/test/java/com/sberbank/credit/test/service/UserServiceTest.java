package com.sberbank.credit.test.service;

import com.sberbank.credit.model.entities.UserEntity;
import com.sberbank.credit.repository.UserRepository;
import com.sberbank.credit.service.user.UserService;
import com.sberbank.credit.test.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String login;
    private String notEncodedPass = "123456";
    private String encodedPass;
    private UserEntity userEntity;

    @Before
    public void before() {
        login = "testLogin";

        encodedPass = passwordEncoder.encode(notEncodedPass);
        userEntity = new UserEntity("Testerov Test Testerovich", login, encodedPass);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(userEntity);
    }


    @Test(expected = BadCredentialsException.class)
    public void testAuthenticate() throws Exception {

        UserEntity user = userService.authenticate(login, notEncodedPass);
        Assert.assertNotNull("User must not be null", user);

        String fakePassword = "123455";
        user = userService.authenticate(login, fakePassword);
    }
}

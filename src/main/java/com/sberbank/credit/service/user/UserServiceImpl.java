package com.sberbank.credit.service.user;

import com.sberbank.credit.model.UserEntity;
import com.sberbank.credit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserEntity authenticate(String login, String password) throws Exception {
        UserEntity user = userRepository.findByLogin(login);

        if (user == null)
            throw new Exception("User not found");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new Exception("Wrong password");

        return user;
    }

    @PostConstruct
    public void init() {
        addUser(new UserEntity("Testerov Tester", "tester", "123456"));
    }
}

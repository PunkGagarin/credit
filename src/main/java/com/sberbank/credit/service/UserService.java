package com.gagarin.credit.service;

import com.gagarin.credit.model.UserEntity;

public interface UserService {

    UserEntity findByLogin(String login);

    void addUser(UserEntity user);
}

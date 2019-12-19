package com.sberbank.credit.service;

import com.sberbank.credit.model.UserEntity;

public interface UserService {

    UserEntity findByLogin(String login);

    void addUser(UserEntity user);
}

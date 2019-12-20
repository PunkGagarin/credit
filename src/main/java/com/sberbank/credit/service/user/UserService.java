package com.sberbank.credit.service.user;

import com.sberbank.credit.model.entities.UserEntity;

public interface UserService {

    UserEntity findByLogin(String login);

    void addUser(UserEntity user);

    UserEntity authenticate(String login, String password) throws Exception;
}

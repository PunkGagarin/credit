package com.sberbank.credit.repository;

import com.sberbank.credit.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByLogin(String login);
}

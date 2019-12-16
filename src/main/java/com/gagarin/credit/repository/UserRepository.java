package com.gagarin.credit.repository;

import com.gagarin.credit.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}

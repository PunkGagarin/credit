package com.gagarin.credit.repository;

import com.gagarin.credit.model.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
}

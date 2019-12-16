package com.gagarin.credit.repository;

import com.gagarin.credit.model.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
}

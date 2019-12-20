package com.sberbank.credit.repository;

import com.sberbank.credit.model.entity.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
}

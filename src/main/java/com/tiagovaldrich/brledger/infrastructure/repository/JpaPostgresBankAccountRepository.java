package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.infrastructure.entities.BankAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPostgresBankAccountRepository extends JpaRepository<BankAccountJpaEntity, Long> {
    BankAccountJpaEntity getByBankAndBranchAndNumber(String bank, String branch, String number);
}

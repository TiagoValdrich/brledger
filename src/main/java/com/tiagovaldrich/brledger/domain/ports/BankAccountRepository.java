package com.tiagovaldrich.brledger.domain.ports;

import com.tiagovaldrich.brledger.domain.entities.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {

    BankAccount create(BankAccount bankAccount);

    List<BankAccount> get();

    Optional<BankAccount> getById(Long id);
}

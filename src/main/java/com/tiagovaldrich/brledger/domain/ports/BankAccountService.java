package com.tiagovaldrich.brledger.domain.ports;

import com.tiagovaldrich.brledger.application.dto.BankAccountDTO;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount createBankAccount(BankAccountDTO bankAccount);

    List<BankAccount> getBankAccounts();

    BankAccount getBankAccountById(Long id);
}

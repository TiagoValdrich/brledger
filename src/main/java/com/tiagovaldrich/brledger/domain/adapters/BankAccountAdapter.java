package com.tiagovaldrich.brledger.domain.adapters;

import com.tiagovaldrich.brledger.application.dto.BankAccountDTO;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.domain.ports.BankAccountService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BankAccountAdapter implements BankAccountService {

    private final BankAccountRepository repository;


    @Override
    public BankAccount createBankAccount(BankAccountDTO bankAccountDTO) {
        var bankAccount = BankAccount.fromDTO(bankAccountDTO);

        return repository.create(bankAccount);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return repository.get();
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return repository.getById(id).orElse(null);
    }
}

package com.tiagovaldrich.brledger.infrastructure.mappers;

import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.infrastructure.entities.BankAccountJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccountJpaEntity fromDomainEntityToJpa(BankAccount bankAccount) {
        var bankAccountJpa = BankAccountJpaEntity.builder()
                .holderName(bankAccount.getHolderName())
                .bank(bankAccount.getBank())
                .branch(bankAccount.getBranch())
                .number(bankAccount.getNumber())
                .txId(bankAccount.getTxId())
                .external(bankAccount.getExternal())
                .build();

        bankAccountJpa.setId(bankAccount.getId());

        return bankAccountJpa;
    }

    public BankAccount fromJpaEntityToDomain(BankAccountJpaEntity bankAccountJpa) {
        return BankAccount.builder()
                .id(bankAccountJpa.getId())
                .holderName(bankAccountJpa.getHolderName())
                .bank(bankAccountJpa.getBank())
                .branch(bankAccountJpa.getBranch())
                .number(bankAccountJpa.getNumber())
                .txId(bankAccountJpa.getTxId())
                .external(bankAccountJpa.getExternal())
                .build();
    }
}

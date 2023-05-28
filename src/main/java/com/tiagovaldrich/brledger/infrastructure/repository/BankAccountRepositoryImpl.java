package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.common.service.DefaultTimezoneService;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.infrastructure.entities.BankAccountJpaEntity;
import com.tiagovaldrich.brledger.infrastructure.mappers.BankAccountMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final JpaPostgresBankAccountRepository jpaPostgresBankAccountRepository;
    private final BankAccountMapper bankAccountMapper;


    @Override
    public BankAccount create(BankAccount bankAccount) {
        var jpaBankAccount = bankAccountMapper.fromDomainEntityToJpa(bankAccount);
        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());

        jpaBankAccount.setCreatedAt(currentTime);
        jpaBankAccount.setUpdatedAt(currentTime);
        jpaBankAccount = jpaPostgresBankAccountRepository.save(jpaBankAccount);

        return bankAccountMapper.fromJpaEntityToDomain(jpaBankAccount);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return jpaPostgresBankAccountRepository
                .findAll()
                .parallelStream()
                .map(bankAccountMapper::fromJpaEntityToDomain)
                .toList();
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long id) {
        BankAccountJpaEntity jpaBankAccount;

        try {
            jpaBankAccount = jpaPostgresBankAccountRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return Optional.of(null);
        }

        return Optional.of(bankAccountMapper.fromJpaEntityToDomain(jpaBankAccount));
    }
}

package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.common.service.DefaultTimezoneService;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.infrastructure.entities.BankAccountJpaEntity;
import com.tiagovaldrich.brledger.infrastructure.mappers.BankAccountMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final JpaPostgresBankAccountRepository jpaPostgresBankAccountRepository;
    private final BankAccountMapper bankAccountMapper;


    @Override
    @Transactional
    public BankAccount create(BankAccount bankAccount) {
        var jpaBankAccount = bankAccountMapper.fromDomainEntityToJpa(bankAccount);
        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());

        jpaBankAccount.setCreatedAt(currentTime);
        jpaBankAccount.setUpdatedAt(currentTime);
        jpaBankAccount = jpaPostgresBankAccountRepository.save(jpaBankAccount);

        return bankAccountMapper.fromJpaEntityToDomain(jpaBankAccount);
    }

    @Override
    public List<BankAccount> get() {
        return jpaPostgresBankAccountRepository
                .findAll()
                .parallelStream()
                .map(bankAccountMapper::fromJpaEntityToDomain)
                .toList();
    }

    @Override
    @Transactional
    public Optional<BankAccount> getById(Long id) {
        BankAccountJpaEntity jpaBankAccount;

        try {
            jpaBankAccount = jpaPostgresBankAccountRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }

        return Optional.of(bankAccountMapper.fromJpaEntityToDomain(jpaBankAccount));
    }

    @Override
    @Transactional
    public Optional<BankAccount> getByBankAndBranchAndNumber(String bank, String branch, String number) {
        var jpaBankAccount = jpaPostgresBankAccountRepository.getByBankAndBranchAndNumber(bank, branch, number);

        if (jpaBankAccount == null) {
            return Optional.empty();
        }

        return Optional.of(bankAccountMapper.fromJpaEntityToDomain(jpaBankAccount));
    }
}

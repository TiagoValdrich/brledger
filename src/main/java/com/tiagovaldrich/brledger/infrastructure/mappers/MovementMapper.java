package com.tiagovaldrich.brledger.infrastructure.mappers;

import com.tiagovaldrich.brledger.domain.entities.Movement;
import com.tiagovaldrich.brledger.infrastructure.entities.MovementJpaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovementMapper {

    private final BankAccountMapper bankAccountMapper;

    public MovementJpaEntity fromDomainEntityToJpa(Movement movement) {
        var bankAccount = bankAccountMapper.fromDomainEntityToJpa(movement.getBankAccount());
        var counterpartyBankAccount = bankAccountMapper.fromDomainEntityToJpa(movement.getCounterpartyBankAccount());

        return MovementJpaEntity.builder()
                .id(movement.getId())
                .date(movement.getDate())
                .type(movement.getType())
                .transactionType(movement.getTransactionType())
                .description(movement.getDescription())
                .bankAccount(bankAccount)
                .counterpartyBankAccount(counterpartyBankAccount)
                .value(movement.getValue())
                .balance(movement.getBalance())
                .referenceId(movement.getReferenceId())
                .createdAt(movement.getCreatedAt())
                .updatedAt(movement.getUpdatedAt())
                .build();
    }

    public Movement fromJpaEntityToDomain(MovementJpaEntity movementJpaEntity) {
        var bankAccount = bankAccountMapper.fromJpaEntityToDomain(movementJpaEntity.getBankAccount());
        var counterpartyBankAccount = bankAccountMapper.fromJpaEntityToDomain(movementJpaEntity.getCounterpartyBankAccount());

        return Movement.builder()
                .id(movementJpaEntity.getId())
                .date(movementJpaEntity.getDate())
                .type(movementJpaEntity.getType())
                .transactionType(movementJpaEntity.getTransactionType())
                .description(movementJpaEntity.getDescription())
                .bankAccount(bankAccount)
                .counterpartyBankAccount(counterpartyBankAccount)
                .value(movementJpaEntity.getValue())
                .balance(movementJpaEntity.getBalance())
                .referenceId(movementJpaEntity.getReferenceId())
                .createdAt(movementJpaEntity.getCreatedAt())
                .updatedAt(movementJpaEntity.getUpdatedAt())
                .build();
    }
}

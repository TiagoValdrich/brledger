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
        var originBankAccount = bankAccountMapper.fromDomainEntityToJpa(movement.getOrigin());
        var destinationBankAccount = bankAccountMapper.fromDomainEntityToJpa(movement.getDestination());

        return MovementJpaEntity.builder()
                .id(movement.getId())
                .date(movement.getDate())
                .type(movement.getType())
                .description(movement.getDescription())
                .origin(originBankAccount)
                .destination(destinationBankAccount)
                .value(movement.getValue())
                .referenceId(movement.getReferenceId())
                .createdAt(movement.getCreatedAt())
                .updatedAt(movement.getUpdatedAt())
                .build();
    }

    public Movement fromJpaEntityToDomain(MovementJpaEntity movementJpaEntity) {
        var originBankAccount = bankAccountMapper.fromJpaEntityToDomain(movementJpaEntity.getOrigin());
        var destinationBankAccount = bankAccountMapper.fromJpaEntityToDomain(movementJpaEntity.getDestination());

        return Movement.builder()
                .id(movementJpaEntity.getId())
                .date(movementJpaEntity.getDate())
                .type(movementJpaEntity.getType())
                .description(movementJpaEntity.getDescription())
                .origin(originBankAccount)
                .destination(destinationBankAccount)
                .value(movementJpaEntity.getValue())
                .referenceId(movementJpaEntity.getReferenceId())
                .createdAt(movementJpaEntity.getCreatedAt())
                .updatedAt(movementJpaEntity.getUpdatedAt())
                .build();
    }
}

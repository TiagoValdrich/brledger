package com.tiagovaldrich.brledger.service;

import com.tiagovaldrich.brledger.dto.MovementDTO;
import com.tiagovaldrich.brledger.entities.Movement;
import com.tiagovaldrich.brledger.exceptions.BusinessRuleException;

public class MovementService {
    public void validateMovementCreationContract(MovementDTO movement) throws BusinessRuleException {
        if (movement.value().floatValue() < 0) {
            throw new BusinessRuleException("Movement value can't be negative");
        }

        if (movement.referenceId() == null || movement.referenceId().isEmpty() || movement.referenceId().isBlank()) {
            throw new BusinessRuleException("Movement reference id can't be null or empty");
        }
    }

    public Movement buildMovementFromDTO(MovementDTO movement) {
        return new Movement(
                movement.id(),
                movement.date(),
                movement.description(),
                movement.origin(),
                movement.destination(),
                movement.type(),
                movement.value(),
                movement.referenceId(),
                movement.createdAt(),
                movement.updatedAt()
        );
    }
}

package com.tiagovaldrich.brledger.domain.entities;

import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.exceptions.BusinessRuleException;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Long id;
    private ZonedDateTime date;
    private String description;
    private BankAccount origin;
    private BankAccount destination;
    private EntryType type;
    private Long value;
    private String referenceId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static Movement fromDTO(MovementDTO movement) {
        return new Movement(
                movement.id(),
                movement.date(),
                movement.description(),
                null,
                null,
                movement.type(),
                movement.value(),
                movement.referenceId(),
                movement.createdAt(),
                movement.updatedAt()
        );
    }

    public MovementDTO toDTO() {
        return new MovementDTO(
                this.getId(),
                this.getDate(),
                this.getDescription(),
                this.origin.getId(),
                this.destination.getId(),
                this.getType(),
                this.getValue(),
                this.getReferenceId(),
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }

    public void validate() throws BusinessRuleException {
        if (value.floatValue() < 0) {
            throw new BusinessRuleException("MovementJpaEntity value can't be negative");
        }

        if (referenceId == null || referenceId.isEmpty() || referenceId.isBlank()) {
            throw new BusinessRuleException("MovementJpaEntity reference id can't be null or empty");
        }
    }
}

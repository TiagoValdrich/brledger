package com.tiagovaldrich.brledger.domain.entities;

import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.enums.TransactionType;
import com.tiagovaldrich.brledger.common.exceptions.BusinessRuleException;
import lombok.*;
import org.hibernate.Transaction;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Long id;
    private ZonedDateTime date;
    private String description;
    private BankAccount bankAccount;
    private BankAccount counterpartyBankAccount;
    private EntryType type;
    private TransactionType transactionType;
    private Long value;
    private Long balance;
    private String referenceId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static Movement fromDTO(MovementDTO movement) {
        return new Movement(
                movement.id(),
                movement.date(),
                movement.description(),
                BankAccount.fromDTO(movement.bankAccount()),
                BankAccount.fromDTO(movement.counterpartyBankAccount()),
                movement.type(),
                movement.transactionType(),
                movement.value(),
                movement.balance(),
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
                this.bankAccount.toDTO(),
                this.counterpartyBankAccount.toDTO(),
                this.getType(),
                this.getTransactionType(),
                this.getValue(),
                this.getBalance(),
                this.getReferenceId(),
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }
}

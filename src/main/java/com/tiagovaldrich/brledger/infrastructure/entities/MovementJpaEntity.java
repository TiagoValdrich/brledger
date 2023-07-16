package com.tiagovaldrich.brledger.infrastructure.entities;

import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "movement")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementJpaEntity {

    @Id
    @SequenceGenerator(name = "movement_id_seq_gen", sequenceName = "movement_id_seq_gen", allocationSize = 1)
    @GeneratedValue(generator = "movement_id_seq_gen")
    private Long id;

    private ZonedDateTime date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "bank_account")
    private BankAccountJpaEntity bankAccount;

    @ManyToOne
    @JoinColumn(name = "counterparty_bank_account")
    private BankAccountJpaEntity counterpartyBankAccount;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    private Long value;

    private Long balance;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}

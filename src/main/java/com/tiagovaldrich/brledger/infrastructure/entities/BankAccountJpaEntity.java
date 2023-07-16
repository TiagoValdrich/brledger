package com.tiagovaldrich.brledger.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountJpaEntity extends BaseEntity {

    @Column(name = "holder_name")
    private String holderName;

    private String bank;

    private String branch;

    private String number;

    @Column(name = "tx_id")
    private String txId;

    private Boolean external;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<MovementJpaEntity> createdMovementations;

    @OneToMany(mappedBy = "counterpartyBankAccount", fetch = FetchType.LAZY)
    private List<MovementJpaEntity> receivedMovementations;
}

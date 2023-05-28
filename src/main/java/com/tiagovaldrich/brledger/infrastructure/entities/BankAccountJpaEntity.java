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

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<MovementJpaEntity> createdMovementations;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<MovementJpaEntity> receivedMovementations;
}

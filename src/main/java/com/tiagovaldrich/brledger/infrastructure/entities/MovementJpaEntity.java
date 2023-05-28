package com.tiagovaldrich.brledger.infrastructure.entities;

import com.tiagovaldrich.brledger.common.enums.EntryType;
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
    @JoinColumn(name = "origin")
    private BankAccountJpaEntity origin;

    @ManyToOne
    @JoinColumn(name = "destination")
    private BankAccountJpaEntity destination;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    private Long value;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}

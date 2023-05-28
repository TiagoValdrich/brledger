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
    @GeneratedValue(generator = "movement_id_seq_gen")
    @SequenceGenerator(name = "movement_id_seq_gen", sequenceName = "movement_id_seq_gen", allocationSize = 1)
    private Long id;

    private ZonedDateTime date;

    private String description;

    @ManyToOne
    private BankAccountJpaEntity origin;

    @ManyToOne
    private BankAccountJpaEntity destination;

    private EntryType type;

    private Long value;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}

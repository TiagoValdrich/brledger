package com.tiagovaldrich.brledger.entities;

import com.tiagovaldrich.brledger.enums.EntryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor()
public class Movement {
    private Long id;
    private ZonedDateTime date;
    private String description;
    private BankAccount origin;
    private BankAccount destination;
    private EntryType type;
    private BigDecimal value;
    private String referenceId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

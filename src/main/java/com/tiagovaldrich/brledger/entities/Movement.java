package com.tiagovaldrich.brledger.entities;

import com.tiagovaldrich.brledger.enums.EntryType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Movement {
    private Long id;
    private ZonedDateTime date;
    private String description;
    private BankAccount origin;
    private BankAccount destination;
    private EntryType type;
    private BigDecimal value;
    private String referenceId;
}

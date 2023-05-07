package com.tiagovaldrich.brledger.entities;

import com.tiagovaldrich.brledger.enums.EntryType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BankStatementEntry {
    private Long id;
    private BankAccount bankAccount;
    private Long movementId;
    private ZonedDateTime date;
    private String description;
    private EntryType type;
    private BigDecimal value;
    private BigDecimal balance;
}

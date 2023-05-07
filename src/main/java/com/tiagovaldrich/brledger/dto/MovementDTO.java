package com.tiagovaldrich.brledger.dto;


import com.tiagovaldrich.brledger.entities.BankAccount;
import com.tiagovaldrich.brledger.enums.EntryType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record MovementDTO(
        Long id,
        ZonedDateTime date,
        String description,
        BankAccount origin,
        BankAccount destination,
        EntryType type,
        BigDecimal value,
        String referenceId,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}

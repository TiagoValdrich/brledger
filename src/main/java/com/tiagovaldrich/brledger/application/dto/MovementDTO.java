package com.tiagovaldrich.brledger.application.dto;


import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.common.enums.EntryType;

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

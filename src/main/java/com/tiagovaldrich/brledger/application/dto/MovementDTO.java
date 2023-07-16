package com.tiagovaldrich.brledger.application.dto;

import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.enums.TransactionType;

import java.time.ZonedDateTime;

public record MovementDTO(
        Long id,
        ZonedDateTime date,
        String description,
        BankAccountDTO bankAccount,
        BankAccountDTO counterpartyBankAccount,
        EntryType type,
        TransactionType transactionType,
        Long value,
        Long balance,
        String referenceId,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}

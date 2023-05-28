package com.tiagovaldrich.brledger.application.dto;

import com.tiagovaldrich.brledger.common.enums.EntryType;

import java.time.ZonedDateTime;

public record MovementDTO(
        Long id,
        ZonedDateTime date,
        String description,
        Long origin,
        Long destination,
        EntryType type,
        Long value,
        String referenceId,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}

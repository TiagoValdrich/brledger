package com.tiagovaldrich.brledger.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public record BankAccountDTO (
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String holderName,
        String bank,
        String branch,
        String number,
        String txId,
        Boolean external
) {}

package com.tiagovaldrich.brledger.domain.entities;

import java.time.ZonedDateTime;

public class BankBalance {
    private Long id;
    private ZonedDateTime date;
    private Long amount;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

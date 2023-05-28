package com.tiagovaldrich.brledger.domain.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BankAccountBalance {
    private Long id;
    private BankAccount bankAccount;
    private ZonedDateTime date;
    private BigDecimal amount;
}
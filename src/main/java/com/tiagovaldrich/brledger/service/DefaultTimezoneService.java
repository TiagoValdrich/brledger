package com.tiagovaldrich.brledger.service;

import java.time.ZoneId;

public class DefaultTimezoneService {
    private static final String DEFAULT_ZONE = "America/Sao_Paulo";

    public static ZoneId obtain() {
        return ZoneId.of(DEFAULT_ZONE);
    }
}

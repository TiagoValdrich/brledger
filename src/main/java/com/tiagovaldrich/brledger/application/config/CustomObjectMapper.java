package com.tiagovaldrich.brledger.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper extends ObjectMapper {

    CustomObjectMapper() {
        super();
        this.addModules();
    }

    private void addModules() {
        this.registerModule(new JavaTimeModule());
    }
}

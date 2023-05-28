package com.tiagovaldrich.brledger.application.config;

import com.tiagovaldrich.brledger.domain.adapters.MovementAdapter;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.domain.ports.MovementRepository;
import com.tiagovaldrich.brledger.domain.ports.MovementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovementConfig {

    @Bean
    public MovementService movementService(
            MovementRepository movementRepository,
            BankAccountRepository bankAccountRepository
    ) {
        return new MovementAdapter(movementRepository, bankAccountRepository);
    }
}

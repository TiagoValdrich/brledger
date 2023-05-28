package com.tiagovaldrich.brledger.application.config;

import com.tiagovaldrich.brledger.domain.adapters.BankAccountAdapter;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.domain.ports.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankAccountConfig {

    @Bean
    BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountAdapter(repository);
    }
}

package com.tiagovaldrich.brledger.application.api;

import com.tiagovaldrich.brledger.application.dto.BankAccountDTO;
import com.tiagovaldrich.brledger.domain.ports.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank_account")
@RestController
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBankAccount(@RequestBody BankAccountDTO dto) {
        bankAccountService.createBankAccount(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountDTO getBankAccount(@PathVariable("id") Long id) {
        var bankAccount = bankAccountService.getBankAccountById(id);

        return bankAccount.toDTO();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccountDTO> getBankAccounts() {
        return bankAccountService
                .getBankAccounts()
                .stream()
                .map(bankAccount -> bankAccount.toDTO())
                .toList();
    }
}

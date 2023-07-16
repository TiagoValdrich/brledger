package com.tiagovaldrich.brledger.domain.adapters;

import com.tiagovaldrich.brledger.application.dto.BankAccountDTO;
import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.domain.entities.MovementDescription;
import com.tiagovaldrich.brledger.domain.ports.BankAccountRepository;
import com.tiagovaldrich.brledger.domain.ports.MovementService;
import com.tiagovaldrich.brledger.common.exceptions.BusinessRuleException;
import com.tiagovaldrich.brledger.domain.entities.Movement;
import com.tiagovaldrich.brledger.domain.ports.MovementRepository;
import com.tiagovaldrich.brledger.common.service.DefaultTimezoneService;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
public class MovementAdapter implements MovementService {

    private MovementRepository repository;
    private BankAccountRepository bankAccountRepository;

    public List<Movement> getMovements() {
        return repository.getMovements();
    }

    @Override
    public Movement createMovementation(MovementDTO movementDTO) throws BusinessRuleException {
        var movement = Movement.fromDTO(movementDTO);
        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());
        var bankAccount = getBankAccount(movementDTO.bankAccount());
        var counterPartyBankAccount = getBankAccount(movementDTO.counterpartyBankAccount());

        if (bankAccount.getExternal()) {
            throw new BusinessRuleException("Cannot create movement for external bank account");
        }

        movement.setBankAccount(bankAccount);
        movement.setCounterpartyBankAccount(counterPartyBankAccount);

        var currentBalance = getCurrentBalance(bankAccount.getId());
        var newBalance = calculateNewBalance(currentBalance, movement);
        var description = MovementDescription.buildDescription(movement);

        movement.setId(null);
        movement.setDate(currentTime);
        movement.setDescription(description);
        movement.setBalance(newBalance);
        movement.setCreatedAt(currentTime);
        movement.setUpdatedAt(currentTime);

        return repository.create(movement);
    }

    public Movement getMovementById(Long id) {
        return repository.getById(id).orElse(null);
    }

    private BankAccount getBankAccount(BankAccountDTO bankAccountDTO) {
        if (bankAccountDTO.id() != null) {
            return bankAccountRepository
                    .getById(bankAccountDTO.id())
                    .orElseThrow(() -> new BusinessRuleException("Bank account not found"));
        }

        var existingBankAccount = bankAccountRepository
                .getByBankAndBranchAndNumber(bankAccountDTO.bank(), bankAccountDTO.branch(), bankAccountDTO.number());

        if (existingBankAccount.isPresent()) {
            var bankAccount = existingBankAccount.get();

            if (bankAccount.getTxId().equals(bankAccountDTO.txId())) {
                return bankAccount;
            }

            throw new BusinessRuleException("Bank account already exists but belongs to another legal or natural person");
        }

        return bankAccountRepository.create(BankAccount.fromDTO(bankAccountDTO));
    }

    private Long getCurrentBalance(Long bankAccountId) {
        var lastMovementation = repository.getLastAccountMovement(bankAccountId);

        if (lastMovementation.isPresent()) {
            return lastMovementation.get().getBalance();
        }

        return 0L;
    }

    private Long calculateNewBalance(Long currentBalance, Movement movement) {
        if (movement.getType() == EntryType.DEBIT) {
            return currentBalance - movement.getValue();
        }

        return currentBalance + movement.getValue();
    }
}

package com.tiagovaldrich.brledger.domain.adapters;

import com.tiagovaldrich.brledger.application.dto.MovementDTO;
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

        Runnable bankAccountNotFound = () -> {
            throw new BusinessRuleException("bank account not found");
        };

        bankAccountRepository
                .getBankAccountById(movementDTO.origin())
                .ifPresentOrElse(
                        origin -> movement.setOrigin(origin),
                        bankAccountNotFound
                );
        bankAccountRepository
                .getBankAccountById(movementDTO.destination())
                .ifPresentOrElse(
                        destination -> movement.setDestination(destination),
                        bankAccountNotFound
                );

        movement.validate();
        movement.setId(null);
        movement.setCreatedAt(currentTime);
        movement.setUpdatedAt(currentTime);

        return repository.create(movement);
    }

    public Movement getMovementById(Long id) {
        return repository.getById(id).orElse(null);
    }
}

package com.tiagovaldrich.brledger.adapters;

import com.tiagovaldrich.brledger.dto.MovementDTO;
import com.tiagovaldrich.brledger.entities.Movement;
import com.tiagovaldrich.brledger.ports.MovementRepository;
import com.tiagovaldrich.brledger.service.DefaultTimezoneService;
import com.tiagovaldrich.brledger.service.MovementService;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
public class MovementAdapter {

    private MovementService service;
    private MovementRepository repository;

    public Movement createMovementation(MovementDTO movementDTO) {
        service.validateMovementCreationContract(movementDTO);

        var movement = service.buildMovementFromDTO(movementDTO);
        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());

        movement.setId(null);
        movement.setCreatedAt(currentTime);
        movement.setUpdatedAt(currentTime);

        return repository.create(movement);
    }

    public List<Movement> getMovements() {
        return repository.getMovements();
    }
}

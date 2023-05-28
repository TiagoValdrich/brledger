package com.tiagovaldrich.brledger.domain.ports;

import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.domain.entities.Movement;

import java.util.List;

public interface MovementService {
    Movement createMovementation(MovementDTO movementDTO);
    List<Movement> getMovements();

    Movement getMovementById(Long id);
}

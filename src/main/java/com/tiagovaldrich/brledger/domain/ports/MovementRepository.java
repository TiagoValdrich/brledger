package com.tiagovaldrich.brledger.domain.ports;

import com.tiagovaldrich.brledger.domain.entities.Movement;

import java.util.List;
import java.util.Optional;

public interface MovementRepository {
    Movement create(Movement movement);

    List<Movement> getMovements();

    Optional<Movement> getById(Long id);
}

package com.tiagovaldrich.brledger.ports;

import com.tiagovaldrich.brledger.entities.Movement;

import java.util.List;

public interface MovementRepository {
    Movement create(Movement movement);
    List<Movement> getMovements();
}

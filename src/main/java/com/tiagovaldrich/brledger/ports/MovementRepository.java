package com.tiagovaldrich.brledger.ports;

import com.tiagovaldrich.brledger.entities.Movement;

public interface MovementRepository {
    Movement create(Movement movement);
}

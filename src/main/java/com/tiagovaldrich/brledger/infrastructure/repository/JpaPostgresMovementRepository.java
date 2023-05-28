package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.infrastructure.entities.MovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostgresMovementRepository extends JpaRepository<MovementJpaEntity, Long> {
}

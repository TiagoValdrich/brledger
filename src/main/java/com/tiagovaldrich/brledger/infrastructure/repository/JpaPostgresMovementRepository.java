package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.infrastructure.entities.MovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostgresMovementRepository extends JpaRepository<MovementJpaEntity, Long> {

    @Query("SELECT m FROM MovementJpaEntity m INNER JOIN m.bankAccount b WHERE b.id = :bankAccountId ORDER BY m.createdAt DESC LIMIT 1")
    MovementJpaEntity getLastAccountMovement(Long bankAccountId);
}

package com.tiagovaldrich.brledger.infrastructure.repository;

import com.tiagovaldrich.brledger.common.service.DefaultTimezoneService;
import com.tiagovaldrich.brledger.domain.entities.Movement;
import com.tiagovaldrich.brledger.domain.ports.MovementRepository;
import com.tiagovaldrich.brledger.infrastructure.entities.MovementJpaEntity;
import com.tiagovaldrich.brledger.infrastructure.mappers.MovementMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MovementRepositoryImpl implements MovementRepository {

    private final MovementMapper movementMapper;
    private final JpaPostgresMovementRepository jpaPostgresMovementRepository;

    @Override
    public Movement create(Movement movement) {
        var jpaMovement = movementMapper.fromDomainEntityToJpa(movement);
        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());

        jpaMovement.setCreatedAt(currentTime);
        jpaMovement.setUpdatedAt(currentTime);
        jpaMovement = jpaPostgresMovementRepository.save(jpaMovement);

        return movementMapper.fromJpaEntityToDomain(jpaMovement);
    }

    @Override
    public List<Movement> getMovements() {
        return jpaPostgresMovementRepository
                .findAll()
                .parallelStream()
                .map(movementMapper::fromJpaEntityToDomain)
                .toList();
    }

    @Override
    public Optional<Movement> getById(Long id) {
        MovementJpaEntity jpaMovement;

        try {
            jpaMovement = jpaPostgresMovementRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }

        return Optional.of(movementMapper.fromJpaEntityToDomain(jpaMovement));
    }

    @Override
    public Optional<Movement> getLastAccountMovement(Long bankAccountId) {
        var jpaMovement = jpaPostgresMovementRepository.getLastAccountMovement(bankAccountId);

        if (jpaMovement == null) {
            return Optional.empty();
        }

        return Optional.of(movementMapper.fromJpaEntityToDomain(jpaMovement));
    }
}

package com.tiagovaldrich.brledger.application.api;

import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.domain.ports.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movement")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovement(@RequestBody MovementDTO dto) {
        this.movementService.createMovementation(dto);
    }

}

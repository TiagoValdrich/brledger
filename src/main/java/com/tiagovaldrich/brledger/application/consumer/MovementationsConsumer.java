package com.tiagovaldrich.brledger.application.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MovementationsConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(id = "movementationsListener", topics = "movementations")
    public void listenMovementations(String movementation) {
        try {
            var movement = parseMessage(movementation);
            log.info("Received a movementation", movement);
        } catch (Exception e) {
            log.error("failed to process message", e);
        }
    }

    private MovementDTO parseMessage(String data) throws JsonProcessingException {
        return objectMapper.readValue(data, MovementDTO.class);
    }
}

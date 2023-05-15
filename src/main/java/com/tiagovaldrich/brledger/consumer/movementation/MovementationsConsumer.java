package com.tiagovaldrich.brledger.consumer.movementation;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MovementationsConsumer {

    @KafkaListener(id = "movementationsListener", topics = "movementations")
    public void listenMovementations(String movementation) {
        System.out.println("Received movementation");
        System.out.println(movementation);
    }
}

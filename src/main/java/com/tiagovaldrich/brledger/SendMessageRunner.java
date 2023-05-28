package com.tiagovaldrich.brledger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tiagovaldrich.brledger.application.config.CustomObjectMapper;
import com.tiagovaldrich.brledger.application.dto.MovementDTO;
import com.tiagovaldrich.brledger.domain.entities.BankAccount;
import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.service.DefaultTimezoneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SendMessageRunner implements ApplicationRunner {

    private final CustomObjectMapper objectMapper;
    private final KafkaTemplate template;

    @Override
    public void run(ApplicationArguments args) {
//        var currentTime = ZonedDateTime.now(DefaultTimezoneService.obtain());
//        var originUUID = UUID.randomUUID();
//        var destinationUUID = UUID.randomUUID();
//        var origin = new BankAccount();
//        var destination = new BankAccount();
//
//        origin.setId(originUUID);
//        origin.setBank("123");
//        origin.setNumber("1234567");
//        origin.setTxId("12345678910");
//        origin.setHolderName("batata");
//
//        destination.setId(destinationUUID);
//        destination.setBank("123");
//        destination.setNumber("1234567");
//        destination.setTxId("12345678910");
//        destination.setHolderName("batata");
//
//        var movement = new MovementDTO(
//          null,
//          currentTime,
//          "PIX CASH IN",
//          origin,
//          destination,
//          EntryType.CREDIT,
//          BigDecimal.valueOf(1000L),
//          "1234567",
//          null,
//          null
//        );
//
//        try {
//            var parsedMovement = parseMovement(movement);
//            log.info("Sending parsed movment");
//            log.info(parsedMovement);
//            this.template.send("movementations", parsedMovement);
//            log.info("Parsed movement sent");
//        } catch (Exception e) {
//            log.error("failed to send movementation to queue", e);
//        }
    }

    private String parseMovement(MovementDTO movement) throws JsonProcessingException {
        return objectMapper.writeValueAsString(movement);
    }
}

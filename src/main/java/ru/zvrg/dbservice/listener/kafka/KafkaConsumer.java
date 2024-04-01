package ru.zvrg.dbservice.listener.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static ru.zvrg.dbservice.utils.constants.Constants.KafkaSettings.*;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = REQUEST_TOPIC_NAME, groupId = GROUP_ID)
    public void consume(String message) {
        log.info("Получено собщение {}", message);
        kafkaProducer.sendMessage(message);
    }
}

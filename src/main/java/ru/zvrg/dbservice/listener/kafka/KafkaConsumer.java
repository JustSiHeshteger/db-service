package ru.zvrg.dbservice.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static ru.zvrg.dbservice.utils.constants.Constants.KafkaSettings.TOPIC_NAME;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = TOPIC_NAME, groupId = "myGroup")
    public void consume(String message) {
        log.info("Получено собщение {}", message);
    }
}

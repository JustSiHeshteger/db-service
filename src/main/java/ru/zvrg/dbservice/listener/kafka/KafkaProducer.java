package ru.zvrg.dbservice.listener.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static ru.zvrg.dbservice.utils.constants.Constants.KafkaSettings.RESPONSE_TOPIC_NAME;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Отправка сообщения в telegram-bot {}", message);
        kafkaTemplate.send(RESPONSE_TOPIC_NAME, message);
    }
}

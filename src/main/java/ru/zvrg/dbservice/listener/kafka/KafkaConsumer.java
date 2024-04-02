package ru.zvrg.dbservice.listener.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.zvrg.dbservice.dto.kafka.Message;

import static ru.zvrg.dbservice.utils.constants.Constants.KafkaSettings.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final KafkaProducer kafkaProducer;
    private final Gson gson;

    @KafkaListener(topics = REQUEST_TOPIC_NAME, groupId = GROUP_ID)
    public void consume(String message) {
        log.info("Получено собщение {}", message);
        kafkaProducer.sendMessage(gson.fromJson(message, Message.class));
    }
}

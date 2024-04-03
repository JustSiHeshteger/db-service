package ru.zvrg.dbservice.listener.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zvrg.dbservice.dto.kafka.Message;

import static ru.zvrg.dbservice.utils.constants.Constants.KafkaSettings.RESPONSE_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public void sendMessage(Message message) {
        log.info("Отправка сообщения в telegram-bot {}", message);
        kafkaTemplate.send(RESPONSE_TOPIC_NAME, gson.toJson(message));
    }
}

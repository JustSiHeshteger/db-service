package ru.zvrg.dbservice.dto.kafka.impl;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.dbservice.dto.kafka.DefaultMessage;

@Data
public class Message implements DefaultMessage {

    //FIXME возможно в будущем изменить на Context
    /**
     * Параметры, которые пришли в сообщении телеграма
     */
    private Update update;

    /**
     * Запрос, который мы будем обрабатывать на сервисе БД
     */
    private String request;

    //FIXME возможно будет dto
    /**
     * Ответ, который мы должны получить (заполняется на стороне db-service)
     */
    private String response;
}

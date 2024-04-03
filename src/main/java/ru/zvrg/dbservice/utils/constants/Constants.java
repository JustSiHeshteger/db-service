package ru.zvrg.dbservice.utils.constants;

public class Constants {

    public static class KafkaSettings {
        public static final String REQUEST_TOPIC_NAME = "request-topic";
        public static final String RESPONSE_TOPIC_NAME = "response-topic";
        public static final String BOOSTRAP_SERVER = "localhost:9092";
        public static final String GROUP_ID = "myGroup";
    }
}

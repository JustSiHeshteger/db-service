package ru.zvrg.dbservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.zvrg.dbservice.dto.kafka.Message;
import ru.zvrg.dbservice.listener.kafka.KafkaProducer;
import ru.zvrg.dbservice.repository.UserRepository;

import static ru.zvrg.dbservice.utils.constants.Constants.SchedulerConstants.SCHEDULER_TIME;

@Service
@RequiredArgsConstructor
public class SchedulerTasks {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @Scheduled(cron = SCHEDULER_TIME)
    public void weatherDistributionByChatId() {
        userRepository.findAll().forEach(user -> kafkaProducer.sendMessage(new Message()));
    }
}

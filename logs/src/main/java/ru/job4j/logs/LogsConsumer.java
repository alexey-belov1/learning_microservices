package ru.job4j.logs;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogsConsumer {

    @KafkaListener(topics = {"${kafka.log-topic}"})
    public void consume(ConsumerRecord<String, String> record) {
        String level = record.key();
        String msg = record.value();
        switch (level) {
            case "INFO" -> log.info(msg);
            case "ERROR" -> log.error(msg);
            case "WARN" -> log.warn(msg);
            case "DEBUG" -> log.debug(msg);
        }
    }
}
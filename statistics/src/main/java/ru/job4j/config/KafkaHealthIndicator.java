package ru.job4j.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.kafka.core.KafkaTemplate;

//Чёт не работает
public class KafkaHealthIndicator extends AbstractHealthIndicator {

    private final KafkaTemplate kafkaTemplate;

    public KafkaHealthIndicator(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        try {
            this.kafkaTemplate.send("test", null).get();
            builder.up();
        } catch (Exception ex) {
            builder.down(ex);
        }
    }
}
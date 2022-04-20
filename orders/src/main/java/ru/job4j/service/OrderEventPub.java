package ru.job4j.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Order;

@Service
public class OrderEventPub {

    @Value("${kafka.order-event-topic}")
    private String orderEventTopic;

    private final KafkaTemplate kafkaTemplate;

    public OrderEventPub(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void orderCreate(final Order order) {
        OrderEvent event = buildEvent(order);
        this.kafkaTemplate.send(orderEventTopic, event);
    }

    private OrderEvent buildEvent(final Order order) {
        return new OrderEvent(
                order.getId(),
                order.getUserId(),
                order.getCost()
        );
    }
}

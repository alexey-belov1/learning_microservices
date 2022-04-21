package ru.job4j.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.event.OrderEvent;

@Slf4j
@Service
public class OrderEventHandler {

    private final StatisticService statisticService;

    public OrderEventHandler(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @KafkaListener(topics = {"${kafka.order-event-topic}"}, containerFactory = "messageKafkaListenerFactory")
    public void consume(final OrderEvent event) {
        this.statisticService.update(event);
    }
}

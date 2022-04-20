package ru.job4j.event;

import lombok.Data;

@Data
public class OrderEvent {

    private Long id;

    private Long userId;

    private Double cost;
}

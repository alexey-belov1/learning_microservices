package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderEvent {

    private Long id;

    private Long userId;

    private Double cost;
}

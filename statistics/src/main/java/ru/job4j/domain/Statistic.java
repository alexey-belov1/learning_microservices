package ru.job4j.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statistic")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Long userId;

    private Long countOrder;

    private Double averageCost;
}

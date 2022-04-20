package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Statistic;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    List<Statistic> findAll();

    Optional<Statistic> findDistinctByUserId(Long id);
}

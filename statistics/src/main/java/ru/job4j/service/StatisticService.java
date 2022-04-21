package ru.job4j.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Statistic;
import ru.job4j.event.OrderEvent;
import ru.job4j.repository.StatisticRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(final StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Transactional(readOnly = true)
    public List<Statistic> findAll() {
        return this.statisticRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Statistic> findById(Long id) {
        return this.statisticRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Statistic> findByUserId(Long id) {
        return this.statisticRepository.findDistinctByUserId(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public Statistic update(OrderEvent event) {
        if (event == null || event.getUserId() == null) {
            throw new RuntimeException();
        }

        Statistic statistic;
        Optional<Statistic> optional = this.statisticRepository.findDistinctByUserId(event.getUserId());
        if (optional.isEmpty()) {
            statistic = new Statistic();
            statistic.setUserId(event.getUserId());
            statistic.setAverageCost(event.getCost());
            statistic.setCountOrder(1L);
        } else {
            statistic = optional.get();
            statistic.setAverageCost((statistic.getAverageCost() + event.getCost()) / 2.0);
            statistic.setCountOrder(statistic.getCountOrder() + 1);
        }

        log.info("Statistic updated: {}", statistic);
        return this.statisticRepository.save(statistic);
    }
}

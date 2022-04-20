package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Statistic;
import ru.job4j.service.StatisticService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(final StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Statistic>> findAll() {
        return new ResponseEntity<>(
            this.statisticService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistic> findById(@PathVariable Long id) {
        Optional<Statistic> res = this.statisticService.findById(id);
        return new ResponseEntity<>(
            res.orElseGet(Statistic::new),
            res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<Statistic> findByUserId(@PathVariable Long id) {
        Optional<Statistic> res = this.statisticService.findByUserId(id);
        return new ResponseEntity<>(
                res.orElseGet(Statistic::new),
                res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}

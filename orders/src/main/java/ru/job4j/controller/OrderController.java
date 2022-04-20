package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Order;
import ru.job4j.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(
            this.orderService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Optional<Order> res = this.orderService.findById(id);
        return new ResponseEntity<>(
            res.orElseGet(Order::new),
            res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<Order>> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.orderService.findByUserId(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(
                orderService.save(order),
                HttpStatus.CREATED
        );
    }
}

package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Order;
import ru.job4j.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPub orderEventPub;

    public OrderService(final OrderRepository userRepository, OrderEventPub orderEventPub) {
        this.orderRepository = userRepository;
        this.orderEventPub = orderEventPub;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Order> findByUserId(Long id) {
        return this.orderRepository.findByUserId(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public Order save(Order order) {
        Order storedOrder = this.orderRepository.save(order);
        this.orderEventPub.orderCreate(storedOrder);
        return storedOrder;
    }
}

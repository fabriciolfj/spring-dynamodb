package com.github.fabriciolfj.springdynamodb.service;

import com.github.fabriciolfj.springdynamodb.entity.Order;
import com.github.fabriciolfj.springdynamodb.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void create(final Order order) {
        order.setCreatedDate(Instant.now());
        orderRepository.save(order);
    }

    public List<Order> findAll(final String customerId, final String orderId) {
        return orderRepository.findAll(customerId, orderId)
                .stream()
                .map(Page::items)
                .flatMap(p -> p.stream())
                .collect(Collectors.toList());
    }

    public List<Order> condition(final String customerId, final double value) {
        return orderRepository.findOrdersByValue(customerId,value)
                .stream()
                .map(Page::items)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void delete(final String customerId, final String orderId) {
        orderRepository.deleteOrder(customerId, orderId);
    }
}

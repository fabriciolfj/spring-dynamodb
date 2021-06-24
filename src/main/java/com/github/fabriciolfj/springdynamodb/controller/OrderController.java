package com.github.fabriciolfj.springdynamodb.controller;

import com.github.fabriciolfj.springdynamodb.entity.Order;
import com.github.fabriciolfj.springdynamodb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{customer}/{order}")
    public List<Order> findAll(@PathVariable("customer") final String customerId, @PathVariable("order") final String orderId) {
        return orderService.findAll(customerId, orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Order order) {
        orderService.create(order);
    }

    @GetMapping("/condition/{customer}/{value}")
    public List<Order> findCondition(@PathVariable("customer") final String customerId, @PathVariable("value") final double value) {
        return orderService.condition(customerId, value);
    }

    @DeleteMapping("/{customer}/{order}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("customer") final String customerId, @PathVariable("order") final String orderId) {
        orderService.delete(customerId, orderId);
    }

}

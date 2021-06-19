package com.github.fabriciolfj.springdynamodb.controller;


import com.github.fabriciolfj.springdynamodb.entity.Customer;
import com.github.fabriciolfj.springdynamodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Customer> findAll() {
        return customerService.findAll();
    }
}

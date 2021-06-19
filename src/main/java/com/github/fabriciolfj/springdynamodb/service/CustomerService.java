package com.github.fabriciolfj.springdynamodb.service;

import com.github.fabriciolfj.springdynamodb.entity.Customer;
import com.github.fabriciolfj.springdynamodb.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void createCustomer(final Customer customer) {
        repository.save(customer);
    }

    public Iterable<Customer> findAll() {
        return repository.findAll();
    }
}

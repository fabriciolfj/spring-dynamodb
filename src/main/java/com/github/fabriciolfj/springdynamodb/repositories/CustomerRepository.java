package com.github.fabriciolfj.springdynamodb.repositories;

import com.github.fabriciolfj.springdynamodb.entity.Customer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, String> {
}

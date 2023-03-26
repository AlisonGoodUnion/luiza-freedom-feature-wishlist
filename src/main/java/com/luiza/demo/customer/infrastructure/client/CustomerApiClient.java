package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerApiClient {
    List<Customer> findAll();
    Optional<Customer> findById(final String id);
}

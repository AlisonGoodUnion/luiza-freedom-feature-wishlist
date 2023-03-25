package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerApiClient {
    Collection<Customer> findAll();
    Optional<Customer> findById(final String id);
}

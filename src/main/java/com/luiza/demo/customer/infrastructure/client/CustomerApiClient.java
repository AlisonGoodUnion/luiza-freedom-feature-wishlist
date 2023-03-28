package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;

import java.util.List;

public interface CustomerApiClient {
    List<Customer> findAll();

    Customer findById(final String id);
}

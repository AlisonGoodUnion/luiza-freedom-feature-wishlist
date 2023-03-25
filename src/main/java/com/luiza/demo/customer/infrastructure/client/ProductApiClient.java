package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.domain.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductApiClient {
    Collection<Product> findAll();
    Optional<Product> findById(final String id);
}

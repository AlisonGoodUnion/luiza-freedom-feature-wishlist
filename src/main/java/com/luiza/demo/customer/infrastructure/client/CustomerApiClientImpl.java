package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerApiClientImpl implements CustomerApiClient {

    @Override
    public Collection<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.empty();
    }
}

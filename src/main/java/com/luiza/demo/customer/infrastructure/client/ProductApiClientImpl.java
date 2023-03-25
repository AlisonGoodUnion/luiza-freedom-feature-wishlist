package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductApiClientImpl implements ProductApiClient{

    @Override
    public Collection<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }
}

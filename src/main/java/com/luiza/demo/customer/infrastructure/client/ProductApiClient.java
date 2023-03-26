package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Product;

import java.util.List;

public interface ProductApiClient {
    List<Product> findAll();
}

package com.luiza.demo.product.infrastructure.client;

import com.luiza.demo.product.domain.model.Product;

import java.util.List;

public interface ProductApiClient {
    List<Product> findAll();
    Product findById(final String idProduct);
}

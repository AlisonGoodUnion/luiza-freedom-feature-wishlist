package com.luiza.demo.customer.domain.service;

import com.luiza.demo.customer.domain.model.Product;

import java.util.Collection;

public interface CustomerWishlistQueryService {
    Collection<Product> getAllProducts(final String idCustomer);
    Product getOneProduct(final String idCustomer);
}

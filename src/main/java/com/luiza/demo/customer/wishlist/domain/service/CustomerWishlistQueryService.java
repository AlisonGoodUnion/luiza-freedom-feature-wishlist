package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface CustomerWishlistQueryService {
    Optional<List<Product>> getAllProducts(final String idCustomer);
    Optional<Product> getOneProduct(final String idCustomer, final String idProduct);
}

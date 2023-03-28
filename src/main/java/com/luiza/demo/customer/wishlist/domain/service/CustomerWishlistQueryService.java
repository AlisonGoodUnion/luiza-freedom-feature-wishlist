package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.product.domain.model.Product;

import java.util.List;

public interface CustomerWishlistQueryService {
    List<Product> getAllProducts(final String idCustomer);

    Product getOneProduct(final String idCustomer, final String idProduct);
}

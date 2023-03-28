package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;

public interface CustomerWishlistMaintainService {
    Wishlist addProduct(final String idCustomer, final String idProduct, final int quantity);
    Wishlist removeProduct(final String idCustomer, final String idProduct);
}

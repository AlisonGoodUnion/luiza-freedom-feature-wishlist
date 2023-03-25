package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Wishlist;

import java.util.Optional;

public interface CustomerWishlistMaintainController {
    /** - Adicionar um produto na Wishlist do cliente; */
    Optional<Wishlist> addProduct(final String idCustomer, final String idProduct);
    /** - Remover um produto da Wishlist do cliente; */
    Optional<Wishlist> removeProduct(final String idCustomer, final String idProduct);
}

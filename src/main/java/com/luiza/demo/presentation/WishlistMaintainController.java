package com.luiza.demo.presentation;

import com.luiza.demo.domain.model.Wishlist;

import java.util.Collection;
import java.util.Optional;

public interface WishlistMaintainController {
    /** - Adicionar um produto na Wishlist do cliente; */
    Optional<Wishlist> addProduct(final String idWishlist, final String idProduct);
    /** - Remover um produto da Wishlist do cliente; */
    Optional<Wishlist> removeProduct(final String idWishlist, final String idProduct);
}

package com.luiza.demo.presentation;

import com.luiza.demo.domain.model.Wishlist;

import java.util.Collection;

public interface WishlistQueryController {

    /** - Consultar todos os produtos da Wishlist do cliente; */
    Collection<Wishlist> getAll(final String idCustomer);
    /** - Consultar se um determinado produto está na Wishlist do cliente; */
    boolean existCheck(final String idWishlist, final String idProduct);

}

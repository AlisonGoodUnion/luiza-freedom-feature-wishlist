package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.exception.customexception.DomainBusinessException;
import org.springframework.stereotype.Service;

@Service
public class WishlistAddProductValidator {

    private static final int MAX_LIMIT = 20;

    public void accept(final Wishlist wishlist, final String idProduct) {
        if (wishlist.getProducts().size() > MAX_LIMIT) {
            throw new DomainBusinessException("Exceeded product limit we can add only 20 products ");
        }

        if (wishlist.getProducts().stream()
                .anyMatch(product -> product.getId().equals(idProduct))) {
            throw new DomainBusinessException("Product already on wishlist");
        }
    }
}

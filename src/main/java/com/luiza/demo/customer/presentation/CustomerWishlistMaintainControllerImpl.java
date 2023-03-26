package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Wishlist;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class CustomerWishlistMaintainControllerImpl implements CustomerWishlistMaintainController {

    @Override
    public Optional<Wishlist> addProduct(String idCustomer, String idProduct) {
        return Optional.empty();
    }

    @Override
    public Optional<Wishlist> removeProduct(String idCustomer, String idProduct) {
        return Optional.empty();
    }
}

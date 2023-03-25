package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/v1/customers}")
public class CustomerWishlistQueryControllerImpl implements CustomerWishlistQueryController {

    @Override
    @GetMapping("/{id}/wishlists")
    public Collection<Product> getAll(@PathVariable(name = "id") final String idCustomer) {
        return Collections.EMPTY_LIST;
    }

    @Override
    @GetMapping("/{id}/wishlists/{idProduct}")
    public Product getProduct(
            @PathVariable(name = "id") String idCustomer,
            @PathVariable(name = "idProduct") String idProduct) {
        return null;
    }
}

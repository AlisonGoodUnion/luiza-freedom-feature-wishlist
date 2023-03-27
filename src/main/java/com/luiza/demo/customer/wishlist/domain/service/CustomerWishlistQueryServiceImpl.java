package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import com.luiza.demo.product.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerWishlistQueryServiceImpl implements CustomerWishlistQueryService {

    private final CustomerWishlistRepository customerWishlistRepository;

    @Autowired
    public CustomerWishlistQueryServiceImpl(CustomerWishlistRepository customerWishlistRepository) {
        this.customerWishlistRepository = customerWishlistRepository;
    }

    @Override
    public Optional<List<Product>> getAllProducts(String idCustomer) {
        Optional<Wishlist> wishlist = this.customerWishlistRepository.findByCustomerId(idCustomer);

        if (wishlist.isEmpty()) {
            throw new IllegalArgumentException("The server found nothing that matches the values used in the query");
        }
        return Optional.of(wishlist.get().getProducts());
    }

    @Override
    public Optional<Product> getOneProduct(final String idCustomer, final String idProduct) {
        Optional<Wishlist> wishlist = this.customerWishlistRepository.findByCustomerId(idCustomer);

        if (wishlist.isEmpty()) {
            throw new IllegalArgumentException("The server found nothing that matches the values used in the query");
        }

        return wishlist.get().getProducts().stream()
                .filter(product -> product.getId().equals(idProduct)).findFirst();
    }
}

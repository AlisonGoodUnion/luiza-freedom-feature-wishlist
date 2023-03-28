package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
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
    public List<Product> getAllProducts(String idCustomer) {
        Optional<Wishlist> wishlist = this.customerWishlistRepository.findByCustomerId(idCustomer);
        isEmptyValidation(wishlist);
        return wishlist.get().getProducts();
    }

    @Override
    public Product getOneProduct(final String idCustomer, final String idProduct) {
        Optional<Wishlist> wishlist = this.customerWishlistRepository.findByCustomerId(idCustomer);
        return getProduct(idProduct, wishlist);
    }

    private static Product getProduct(String idProduct, Optional<Wishlist> wishlist) {
        isEmptyValidation(wishlist);
        return wishlist.get().getProducts().stream()
                .filter(product -> product.getId().equals(idProduct))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer does have this product on wishlist")
                );
    }

    private static void isEmptyValidation(Optional<Wishlist> wishlist) {
        if (wishlist.isEmpty()) {
            throw new ResourceNotFoundException("Wishlist does not exist");
        }
    }
}

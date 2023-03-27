package com.luiza.demo.customer.wishlist.infrastructure.persistence;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerWishlistRepository extends MongoRepository<Wishlist, String> {
    Optional<Wishlist> findByCustomerId(final String idCustomer);
}

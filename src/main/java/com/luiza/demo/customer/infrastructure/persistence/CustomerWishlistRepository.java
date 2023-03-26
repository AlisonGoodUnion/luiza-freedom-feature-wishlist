package com.luiza.demo.customer.infrastructure.persistence;

import com.luiza.demo.customer.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerWishlistRepository extends MongoRepository<Customer, String> {
}

package com.luiza.demo.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@AllArgsConstructor
@Document
public class Wishlist {
    @Id
    private String id;
    @DBRef
    private Customer customer;
    @DBRef
    private List<Product> products;
}

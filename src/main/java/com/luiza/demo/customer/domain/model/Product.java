package com.luiza.demo.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document
public class Product {
    @Id
    private String id;
    private String description;
    private long price;
    private long quantity;
}

package com.luiza.demo.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document
public class Customer {
    @Id
    private String id;
    private String name;
}

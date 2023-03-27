package com.luiza.demo.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
public class Customer {
    @Id
    private String id;
    private String name;
}

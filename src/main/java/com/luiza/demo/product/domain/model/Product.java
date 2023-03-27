package com.luiza.demo.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
public class Product {
    @Id
    private String id;
    private String description;
    private double price;
    private int quantity;

    public void addQuantity(int quantity) {
        this.quantity = quantity;
    }
}

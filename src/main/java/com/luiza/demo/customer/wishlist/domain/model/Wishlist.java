package com.luiza.demo.customer.wishlist.domain.model;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.product.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@Document
public class Wishlist {
    @Id
    private String id;
    private Customer customer;
    private List<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(String idProduct) {
        this.products.removeIf(product -> product.getId().equals(idProduct));
    }
}

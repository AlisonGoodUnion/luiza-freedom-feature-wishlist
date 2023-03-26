package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Product;
import com.luiza.demo.customer.infrastructure.client.ProductApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductQueryControllerImpl implements ProductQueryController {

    private final ProductApiClient productApiClient;

    @Autowired
    public ProductQueryControllerImpl(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    @GetMapping("")
    @Override
    public List<Product> getAll() {
        return this.productApiClient.findAll();
    }

}

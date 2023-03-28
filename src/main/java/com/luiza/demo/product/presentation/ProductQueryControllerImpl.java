package com.luiza.demo.product.presentation;

import com.luiza.demo.product.domain.model.Product;
import com.luiza.demo.product.infrastructure.client.ProductApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(this.productApiClient.findAll());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Product> getOne(@PathVariable(name = "id") final String idProduct) {
        return ResponseEntity.ok(productApiClient.findById(idProduct));
    }
}

package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.infrastructure.client.CustomerApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerQueryControllerImpl implements CustomerQueryController {

    private final CustomerApiClient customerApiClient;

    @Autowired
    public CustomerQueryControllerImpl(CustomerApiClient customerApiClient) {
        this.customerApiClient = customerApiClient;
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerApiClient.findAll());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Customer> getOne(@PathVariable(name = "id") final String idCustomer) {
        return ResponseEntity.ok(customerApiClient.findById(idCustomer));
    }
}

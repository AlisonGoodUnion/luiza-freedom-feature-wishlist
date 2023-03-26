package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.infrastructure.client.CustomerApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Customer> getAll() {
        return customerApiClient.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Customer getOne(@PathVariable(name = "id") final String idCustomer) {
        return customerApiClient.findById(idCustomer)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "The server found nothing that matches the values used in the query")
                );
    }
}

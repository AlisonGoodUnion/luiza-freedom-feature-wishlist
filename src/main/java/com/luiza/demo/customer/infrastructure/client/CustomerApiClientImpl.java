package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class that simulates an integration with a Customer's webservice
 */
@Service
public class CustomerApiClientImpl implements CustomerApiClient {

    @Override
    public List<Customer> findAll() {
        return getCustomers();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.of(getCustomer(id));
    }

    private static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("507f1f77bcf86cd799439011", "Tiago Silva"));
        customers.add(new Customer("54759eb3c090d83494e2d804", "Hilton Santos"));
        return customers;
    }

    private static Customer getCustomer(String id) {
        return getCustomers().stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "The server found nothing that matches the values used in the query")
                );
    }
}

package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public Customer findById(String id) {
        return getCustomer(id);
    }

    private static Customer getCustomer(String id) {
        return getCustomers().stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer does not exist")
                );
    }

    private static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("54759eb3c090d83494e2d804", "Tiago Silva"));
        customers.add(new Customer("54759eb3c090d83494e2d805", "Hilton Santos"));
        return customers;
    }
}

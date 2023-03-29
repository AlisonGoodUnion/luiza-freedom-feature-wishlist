package com.luiza.demo.customer;

import com.luiza.demo.customer.domain.model.Customer;

public class CustomerDataProvider {
    public static Customer getCustomer() {
        return new Customer("54759eb3c090d83494e2d804", "Tiago Silva");
    }
}

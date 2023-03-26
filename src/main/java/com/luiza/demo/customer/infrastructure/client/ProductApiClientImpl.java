package com.luiza.demo.customer.infrastructure.client;

import com.luiza.demo.customer.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that simulates an integration with a Product's webservice
 */
@Service
public class ProductApiClientImpl implements ProductApiClient {

    @Override
    public List<Product> findAll() {
        return getProducts();
    }

    /** MOCK LIST PRODUCTS */
    private static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("507f1f77bcf86cd799439012", "Keyboard", 1.60));
        products.add(new Product("507f1f77bcf86cd799439011", "Laptop computer", 1.70));
        products.add(new Product("507f1f77bcf86cd799439012", "GPU", 1.71));
        products.add(new Product("507f1f77bcf86cd799439013", "Sound card", 1.73));
        products.add(new Product("507f1f77bcf86cd799439014", "Soundbox for pc", 1.74));
        products.add(new Product("507f1f77bcf86cd799439015", "Processor CPU", 1.75));
        products.add(new Product("507f1f77bcf86cd799439016", "Ram memory", 1.76));
        products.add(new Product("507f1f77bcf86cd799439017", "PC cabinet", 1.77));
        products.add(new Product("507f1f77bcf86cd799439019", "PC cooler", 2.70));
        products.add(new Product("507f1f77bcf86cd799439018", "Gamer cabinet", 1.78));
        products.add(new Product("507f1f77bcf86cd799439020", "Gamer Laptop computer", 4.70));
        products.add(new Product("507f1f77bcf86cd799439021", "Gamer GPU", 5.70));
        products.add(new Product("507f1f77bcf86cd799439023", "Gamer Sound card", 1.70));
        products.add(new Product("507f1f77bcf86cd799439023", "Gamer Soundbox for", 6.70));
        products.add(new Product("507f1f77bcf86cd799439024", "Gamer Processor CPU", 7.70));
        products.add(new Product("507f1f77bcf86cd799439025", "Gamer Ram memory", 8.70));
        products.add(new Product("507f1f77bcf86cd799439026", "Gamer PC cabinet", 2.70));
        products.add(new Product("507f1f77bcf86cd799439027", "Gamer PC cooler", 3.70));
        products.add(new Product("507f1f77bcf86cd799439028", "Gamer mousepad", 4.70));
        products.add(new Product("507f1f77bcf86cd799439029", "Webcam", 1.70));
        products.add(new Product("507f1f77bcf86cd799439030", "Watercooler", 3.70));
        return products;
    }
}

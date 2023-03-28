package com.luiza.demo.customer.wishlist.presentation;

import com.google.gson.Gson;
import com.luiza.demo.config.IntegrationTest;
import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.product.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class CustomerWishlistQueryControllerImplTest extends IntegrationTest {

    @BeforeAll
    public void setUp() {
        Customer customer = new Customer("54759eb3c090d83494e2d804", "Tiago Silva");
        Product product = new Product("507f1f77bcf86cd799439010", "Keyboard", 1.60, 1);

        Wishlist wishlistMock = Wishlist.builder()
                .customer(customer)
                .products(List.of(product))
                .build();
        this.getCustomerWishlistRepository().save(wishlistMock);
    }

    @DisplayName("Given customer's have One product into Wishlist When realise some request Then return all customer's products")
    @Test
    void getCustomerWishlistProducts() throws Exception {
        String URI = "/v1/customers/{id}/wishlists/products";
        String idCustomer = "54759eb3c090d83494e2d804";

        MvcResult mvcResult = this.getMockMvc().perform(
                get(URI, idCustomer)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Product[] products = new Gson().fromJson(contentAsString, Product[].class);

        Assertions.assertEquals(1, products.length);
        Product product = Arrays.stream(products).findFirst().get();
        Assertions.assertEquals("507f1f77bcf86cd799439010", product.getId());
        Assertions.assertEquals("Keyboard", product.getDescription());
        Assertions.assertEquals(1.6, product.getPrice());
        Assertions.assertEquals(1, product.getQuantity());
    }
}
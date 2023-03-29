package com.luiza.demo.customer.wishlist.presentation;

import com.google.gson.Gson;
import com.luiza.demo.config.IntegrationTest;
import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.exception.HttpErroInfo;
import com.luiza.demo.product.domain.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class CustomerWishlistQueryControllerImplTest extends IntegrationTest {

    @BeforeAll
    public void setUp() {
        Customer customer = new Customer("54759eb3c090d83494e2d804", "Tiago Silva");
        Product product1 = new Product("507f1f77bcf86cd799439010", "Keyboard", 1.60, 1);
        Product product2 = new Product("507f1f77bcf86cd799439011", "Laptop computer", 1.70, 1);

        Wishlist wishlistMock = Wishlist.builder()
                .customer(customer)
                .products(List.of(product1, product2))
                .build();
        this.getCustomerWishlistRepository().save(wishlistMock);
    }

    @DisplayName("Given Customer's have Products into Wishlist" +
            "When realise some request " +
            "Then return all Customer's Products")
    @Test
    void getCustomerWishlistProducts() throws Exception {
        String URI = "/v1/customers/{id}/wishlists/products";
        String idCustomer = "54759eb3c090d83494e2d804";

        MvcResult mvcResult = this.getMockMvc().perform(
                get(URI, idCustomer)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals(200, mvcResult.getResponse().getStatus());

        Product[] products = new Gson().fromJson(contentAsString, Product[].class);
        assertEquals(2, products.length);

        Product product = Arrays.stream(products).findFirst().get();
        assertEquals("507f1f77bcf86cd799439010", product.getId());
        assertEquals("Keyboard", product.getDescription());
        assertEquals(1.6, product.getPrice());
        assertEquals(1, product.getQuantity());
    }

    @DisplayName("Given Customer's have one product into Wishlist" +
            "When Customer need only one Product " +
            "Then return one Sustomer's Product")
    @Test
    void getCustomerWishlistOneProduct() throws Exception {
        String URI = "/v1/customers/{id}/wishlists/products/{idProduct}";
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct2 = "507f1f77bcf86cd799439011";

        MvcResult mvcResult = this.getMockMvc().perform(
                get(URI, idCustomer, idProduct2)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());

        String contentAsString = response.getContentAsString();
        Product product = new Gson().fromJson(contentAsString, Product.class);

        assertEquals("507f1f77bcf86cd799439011", product.getId());
        assertEquals("Laptop computer", product.getDescription());
        assertEquals(1.7, product.getPrice());
        assertEquals(1, product.getQuantity());
    }

    @DisplayName("Given Wishlist does not exist " +
            "When Customer obtain Products " +
            "Then return error message")
    @Test
    void getCustomerWishlistException() throws Exception {
        String URI = "/v1/customers/{id}/wishlists/products";
        String idCustomer = "54759eb3c090d83494e2d805";

        MvcResult mvcResult = this.getMockMvc().perform(
                get(URI, idCustomer)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(404, response.getStatus());

        String contentAsString = response.getContentAsString();
        HttpErroInfo httpErroInfo = new Gson().fromJson(contentAsString, HttpErroInfo.class);

        assertEquals(404, httpErroInfo.getCode());
        assertEquals("Wishlist does not exist", httpErroInfo.getDescription());
    }

    @DisplayName("Given Wishlist does not exist the Products " +
            "When Customer obtain one Product " +
            "Then return error message")
    @Test
    void getCustomerWishlistProductException() throws Exception {
        String URI = "/v1/customers/{id}/wishlists/products/{idProduct}";
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439012";

        MvcResult mvcResult = this.getMockMvc().perform(
                get(URI, idCustomer, idProduct)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(404, response.getStatus());

        String contentAsString = response.getContentAsString();
        HttpErroInfo httpErroInfo = new Gson().fromJson(contentAsString, HttpErroInfo.class);

        assertEquals(404, httpErroInfo.getCode());
        assertEquals("Customer does have this product on wishlist", httpErroInfo.getDescription());
    }
}
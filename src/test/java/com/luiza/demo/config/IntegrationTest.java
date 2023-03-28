package com.luiza.demo.config;

import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SuppressWarnings("squid:S2187")
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerWishlistRepository customerWishlistRepository;

    @AfterAll
    void clean() {
        this.customerWishlistRepository.deleteAll();
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public CustomerWishlistRepository getCustomerWishlistRepository() {
        return customerWishlistRepository;
    }

}

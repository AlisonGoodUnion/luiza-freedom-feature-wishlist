package com.luiza.demo.customer.wishlist.presentation;

import com.google.gson.Gson;
import com.luiza.demo.config.IntegrationTest;
import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.presentation.requestdto.WishlistRequestDto;
import com.luiza.demo.exception.HttpErroInfo;
import com.luiza.demo.product.ProductDataProvider;
import com.luiza.demo.product.domain.model.Product;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerWishlistMaintainControllerImplTest extends IntegrationTest {

    public static final String V1_CUSTOMERS_WISHLISTS_ADD_PRODUCT = "/v1/customers/{id}/wishlists/products";
    public static final String V1_CUSTOMERS_WISHLISTS_REMOVE_PRODUCT = "/v1/customers/{id}/wishlists/products/{idProduct}";

    @BeforeAll
    public void setUp() {
        Customer customer = new Customer("54759eb3c090d83494e2d805", "Hilton Santos");

        List<Product> productsSize21 = ProductDataProvider.getProducts();
        productsSize21.remove(20);

        Wishlist wishlistMock = Wishlist.builder()
                .customer(customer)
                .products(productsSize21)
                .build();
        this.getCustomerWishlistRepository().save(wishlistMock);
    }

    @DisplayName("Given Customer's execute add Product " +
            "When data ok " +
            "Then return Wishlist")
    @Test
    @Order(1)
    void addProduct() throws Exception {
        String URI = V1_CUSTOMERS_WISHLISTS_ADD_PRODUCT;
        String idCustomer = "54759eb3c090d83494e2d804";

        WishlistRequestDto build = WishlistRequestDto.builder()
                .quantity(1)
                .idProduct("507f1f77bcf86cd799439010")
                .build();
        String requestBody = new Gson().toJson(build);

        MvcResult mvcResult = this.getMockMvc()
                .perform(post(URI, idCustomer)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals(201, mvcResult.getResponse().getStatus());

        Optional<Wishlist> wishlistEntity = this.getCustomerWishlistRepository().findByCustomerId(idCustomer);
        Wishlist wishlistResponse = new Gson().fromJson(contentAsString, Wishlist.class);

        assertEquals(wishlistEntity.get().getId(), wishlistResponse.getId());
        assertEquals("54759eb3c090d83494e2d804", wishlistResponse.getCustomer().getId());
        assertEquals("Tiago Silva", wishlistResponse.getCustomer().getName());

        Product product = wishlistResponse.getProducts().stream().findFirst().get();

        assertEquals("507f1f77bcf86cd799439010", product.getId());
        assertEquals("Keyboard", product.getDescription());
        assertEquals(1.6, product.getPrice());
        assertEquals(1, product.getQuantity());
    }

    @DisplayName("Given Customer's execute add Product " +
            "When Quantity Product is invalid " +
            "Then return Exception")
    @Test
    @Order(2)
    void addProductQuantityInvalid() throws Exception {
        String URI = V1_CUSTOMERS_WISHLISTS_ADD_PRODUCT;
        String idCustomer = "54759eb3c090d83494e2d804";

        WishlistRequestDto build = WishlistRequestDto.builder()
                .quantity(0)
                .idProduct("507f1f77bcf86cd799439011")
                .build();
        String requestBody = new Gson().toJson(build);

        MvcResult mvcResult = this.getMockMvc()
                .perform(post(URI, idCustomer)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        HttpErroInfo httpErroInfo = new Gson().fromJson(contentAsString, HttpErroInfo.class);

        assertEquals(404, httpErroInfo.getCode());
        assertEquals("Invalid Quantity", httpErroInfo.getDescription());

    }

    @DisplayName("Given Customer's execute add Product " +
            "When Product already exist " +
            "Then return exception")
    @Test
    @Order(3)
    void addProductAlredyExistException() throws Exception {
        String URI = V1_CUSTOMERS_WISHLISTS_ADD_PRODUCT;
        String idCustomer = "54759eb3c090d83494e2d804";

        WishlistRequestDto build = WishlistRequestDto.builder()
                .quantity(1)
                .idProduct("507f1f77bcf86cd799439010")
                .build();
        String requestBody = new Gson().toJson(build);

        MvcResult mvcResult = this.getMockMvc()
                .perform(post(URI, idCustomer)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        HttpErroInfo httpErroInfo = new Gson().fromJson(contentAsString, HttpErroInfo.class);

        assertEquals(422, httpErroInfo.getCode());
        assertEquals("Product already on wishlist", httpErroInfo.getDescription());
    }

    @DisplayName("Given Customer's execute add Product " +
            "When Whishlist Product contains 20 Itens " +
            "Then return exception")
    @Test
    @Order(4)
    void addProductMaxLimitException() throws Exception {
        String URI = V1_CUSTOMERS_WISHLISTS_ADD_PRODUCT;
        String idCustomer = "54759eb3c090d83494e2d805";

        WishlistRequestDto build = WishlistRequestDto.builder()
                .quantity(1)
                .idProduct("507f1f77bcf86cd799439030")
                .build();
        String requestBody = new Gson().toJson(build);

        MvcResult mvcResult = this.getMockMvc()
                .perform(post(URI, idCustomer)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        HttpErroInfo httpErroInfo = new Gson().fromJson(contentAsString, HttpErroInfo.class);

        assertEquals(422, httpErroInfo.getCode());
        assertEquals("Exceeded product limit we can add only 20 products", httpErroInfo.getDescription());
    }

    @DisplayName("Given that the Customer wants to delete the Product " +
            "When existing Product " +
            "Then must return Wishlist without Product")
    @Test
    @Order(5)
    void removeProduct() throws Exception {
        String URI = V1_CUSTOMERS_WISHLISTS_REMOVE_PRODUCT;
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";

        MvcResult mvcResult = this.getMockMvc()
                .perform(delete(URI, idCustomer, idProduct)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals(200, mvcResult.getResponse().getStatus());

        Wishlist wishlistDeleteResponse = new Gson().fromJson(contentAsString, Wishlist.class);
        Optional<Wishlist> wishlistEntity = this.getCustomerWishlistRepository().findByCustomerId("54759eb3c090d83494e2d804");

        assertEquals(wishlistEntity.get().getId(), wishlistDeleteResponse.getId());
        assertEquals("54759eb3c090d83494e2d804", wishlistDeleteResponse.getCustomer().getId());
        assertEquals("Tiago Silva", wishlistDeleteResponse.getCustomer().getName());
        assertEquals(0, wishlistDeleteResponse.getProducts().size());
    }

}
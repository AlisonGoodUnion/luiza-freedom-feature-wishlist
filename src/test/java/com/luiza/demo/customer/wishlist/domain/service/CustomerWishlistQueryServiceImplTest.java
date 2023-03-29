package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.config.UnitTest;
import com.luiza.demo.customer.CustomerDataProvider;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
import com.luiza.demo.product.ProductDataProvider;
import com.luiza.demo.product.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class CustomerWishlistQueryServiceImplTest extends UnitTest {

    @InjectMocks
    private CustomerWishlistQueryServiceImpl customerWishlistQueryService;

    @Mock
    private CustomerWishlistRepository customerWishlistRepository;

    @Test
    @DisplayName("Given idCustomer " +
            "When getAll is called " +
            "Then should return all Customer Products")
    void getAllProducts() {
        Product expected = ProductDataProvider.getProduct();
        Wishlist wishlistMock = Wishlist.builder()
                .customer(CustomerDataProvider.getCustomer())
                .products(List.of(expected))
                .build();
        String idCustomer = "54759eb3c090d83494e2d804";

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.of(wishlistMock));

        List<Product> obtained = this.customerWishlistQueryService.getAllProducts(idCustomer);

        Product product = obtained.stream().findFirst().get();
        Assertions.assertEquals(1, obtained.size());
        Assertions.assertEquals(expected.getId(), product.getId());
        Assertions.assertEquals(expected.getDescription(), product.getDescription());
        Assertions.assertEquals(expected.getPrice(), product.getPrice());
        Assertions.assertEquals(expected.getQuantity(), product.getQuantity());
    }

    @Test
    @DisplayName("Given idCustomer and idProduct " +
            "When getOne is called " +
            "Then should return all Customer Products")
    void getOneProducts() {
        Product expected = new Product("507f1f77bcf86cd799439010", "Keyboard", 1.60, 1);
        List<Product> products = ProductDataProvider.getProducts();
        Wishlist wishlistMock = Wishlist.builder()
                .customer(CustomerDataProvider.getCustomer())
                .products(products)
                .build();
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.of(wishlistMock));

        Product obtained = this.customerWishlistQueryService.getOneProduct(idCustomer, idProduct);

        Assertions.assertEquals(expected.getId(), obtained.getId());
        Assertions.assertEquals(expected.getDescription(), obtained.getDescription());
        Assertions.assertEquals(expected.getPrice(), obtained.getPrice());
        Assertions.assertEquals(expected.getQuantity(), obtained.getQuantity());
    }

    @Test
    @DisplayName("Given Wishlist does not exist " +
            "When getAll is called " +
            "Then should return Exception")
    void getAllProductsWishlistException() {
        String idCustomer = "54759eb3c090d83494e2d804";

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistQueryService.getAllProducts(idCustomer);
        });

        Assertions.assertEquals("Wishlist does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Given Wishlist does not exist " +
            "When getOne is called " +
            "Then should return Exception")
    void getOneProductsWishlistException() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistQueryService.getOneProduct(idCustomer, idProduct);
        });

        Assertions.assertEquals("Wishlist does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Given Wishlist does not contains the product " +
            "When getOne is called " +
            "Then should return Exception")
    void getOneProductsWishlistNoProductException() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProductNotExist = "507f1f77bcf86cd799439011";
        Wishlist wishlistMock = Wishlist.builder()
                .customer(CustomerDataProvider.getCustomer())
                .products(List.of(ProductDataProvider.getProduct()))
                .build();

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.of(wishlistMock));

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistQueryService.getOneProduct(idCustomer, idProductNotExist);
        });

        Assertions.assertEquals("Customer does have this product on wishlist", exception.getMessage());
    }
}
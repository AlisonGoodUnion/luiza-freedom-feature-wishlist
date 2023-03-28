package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.config.UnitTest;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.exception.customexception.DomainBusinessException;
import com.luiza.demo.product.ProductDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WishlistAddProductValidatorTest extends UnitTest {

    @InjectMocks
    private WishlistAddProductValidator wishlistAddProductValidator;

    @Test
    @DisplayName("Given Wishlist When There one Product Then should not return exception")
    void sucessValidation() {
        Wishlist wishlistMock = Wishlist.builder()
                .products(List.of(ProductDataProvider.getProduct()))
                .build();
        String idProduct = "507f1f77bcf86cd799439011";

        Assertions.assertDoesNotThrow(() ->
                this.wishlistAddProductValidator.accept(wishlistMock, idProduct)
        );
    }

    @Test
    @DisplayName("Given Wishlist and idProduct When the parametes is null Then Exception")
    void invalidParametersValidation() {
        IllegalArgumentException domainBusinessException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.wishlistAddProductValidator.accept(null, null);
        });

        assertEquals("Invalid parameters", domainBusinessException.getMessage());
    }

    @Test
    @DisplayName("Given Wishlist When With Max Products Then Exception")
    void maxLimitValidation() {
        Wishlist wishlistMock = Wishlist.builder()
                .products(ProductDataProvider.getProducts())
                .build();
        String idProduct = "507f1f77bcf86cd799439010";

        DomainBusinessException domainBusinessException = Assertions.assertThrows(DomainBusinessException.class, () -> {
            this.wishlistAddProductValidator.accept(wishlistMock, idProduct);
        });

        assertEquals("Exceeded product limit we can add only 20 products", domainBusinessException.getMessage());
    }

    @Test
    @DisplayName("Given Wishlist When The Product already on wishlist Then Exception")
    void productAlreadyOn() {
        Wishlist wishlistMock = Wishlist.builder()
                .products(List.of(ProductDataProvider.getProduct()))
                .build();
        String idProduct = "507f1f77bcf86cd799439010";

        DomainBusinessException domainBusinessException = Assertions.assertThrows(DomainBusinessException.class, () -> {

            this.wishlistAddProductValidator.accept(wishlistMock, idProduct);
        });

        assertEquals("Product already on wishlist", domainBusinessException.getMessage());
    }
}
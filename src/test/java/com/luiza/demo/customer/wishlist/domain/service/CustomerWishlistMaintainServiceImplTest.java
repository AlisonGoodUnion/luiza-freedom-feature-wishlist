package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.config.UnitTest;
import com.luiza.demo.customer.CustomerDataProvider;
import com.luiza.demo.customer.infrastructure.client.CustomerApiClientImpl;
import com.luiza.demo.customer.wishlist.WishlistDataProvider;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
import com.luiza.demo.product.ProductDataProvider;
import com.luiza.demo.product.domain.model.Product;
import com.luiza.demo.product.infrastructure.client.ProductApiClientImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerWishlistMaintainServiceImplTest extends UnitTest {

    @InjectMocks
    private CustomerWishlistMaintainServiceImpl customerWishlistMaintainServiceImpl;
    @Mock
    private CustomerWishlistRepository customerWishlistRepository;
    @Mock
    private ProductApiClientImpl productApiClient;
    @Mock
    private CustomerApiClientImpl customerApiClient;
    @Mock
    private WishlistAddProductValidator wishlistAddProductValidator;

    @DisplayName("Given idCustomer idProduct and quantity" +
            "When addProduct is called" +
            "Then should return Wishlist with Products")
    @Test
    void addProduct() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439011";
        int quantity = 1;
        Wishlist expected = WishlistDataProvider.getWishlistEntity();

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.of(expected));
        when(this.productApiClient.findById(idProduct))
                .thenReturn(ProductDataProvider.getProduct2());
        when(this.customerApiClient.findById(idCustomer))
                .thenReturn(CustomerDataProvider.getCustomer());
        when(this.customerWishlistRepository.save(expected))
                .thenReturn(expected);

        Wishlist obtained = this.customerWishlistMaintainServiceImpl.addProduct(idCustomer, idProduct, quantity);

        verify(this.customerWishlistRepository, times(1)).save(expected);
        verify(this.wishlistAddProductValidator, times(1)).accept(expected, idProduct);

        assertEquals(expected.getId(), obtained.getId());
        assertEquals(expected.getProducts(), obtained.getProducts());
        assertEquals(expected.getCustomer(), obtained.getCustomer());
    }

    @DisplayName("Given idCustomer idProduct and quantity" +
            "When addProduct is called and Customer does not have Wishlist" +
            "Then should return new Wishlist with Product")
    @Test
    void addNewWishlistProduct() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";
        int quantity = 1;
        Wishlist expected = WishlistDataProvider.getWishlist();

        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.empty());
        when(this.productApiClient.findById(idProduct))
                .thenReturn(ProductDataProvider.getProduct());
        when(this.customerApiClient.findById(idCustomer))
                .thenReturn(CustomerDataProvider.getCustomer());
        when(this.customerWishlistRepository.save(expected))
                .thenReturn(expected);

        Wishlist obtained = this.customerWishlistMaintainServiceImpl.addProduct(idCustomer, idProduct, quantity);

        verify(this.customerWishlistRepository, times(1)).save(expected);
        assertEquals(expected.getProducts(), obtained.getProducts());
        assertEquals(expected.getCustomer(), obtained.getCustomer());
    }

    @DisplayName("Given idCustomer idProduct and quantity" +
            "When addProduct is called and quandity is Zero" +
            "Then should return exception")
    @Test
    void addProductQuantityZeroException() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";
        int quantity = 0;
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistMaintainServiceImpl.addProduct(idCustomer, idProduct, quantity);
        });

        verifyNoInteractions(this.customerWishlistRepository);
        assertEquals("Invalid Quantity", exception.getMessage());
    }

    @DisplayName("Given idCustomer idProduct and quantity" +
            "When addProduct is called and quandity is more than 999" +
            "Then should return exception")
    @Test
    void addProductQuantity1KMoreException() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439010";
        int quantity = 1000;
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistMaintainServiceImpl.addProduct(idCustomer, idProduct, quantity);
        });

        verifyNoInteractions(this.customerWishlistRepository);
        assertEquals("Invalid Quantity", exception.getMessage());
    }

    @DisplayName("Given idCustomer and idProduct " +
            "When removeProduct is called" +
            "Then should return Without a Product ")
    @Test
    void removeProduct() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439011";
        Product product2 = ProductDataProvider.getProduct2();
        Wishlist expected = WishlistDataProvider.getWishlistEntityWith2Products();
        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.of(expected));
        when(this.customerWishlistRepository.save(expected))
                .thenReturn(expected);

        Wishlist obtained = this.customerWishlistMaintainServiceImpl.removeProduct(idCustomer, idProduct);

        verify(this.customerWishlistRepository, times(1)).save(expected);
        assertFalse(obtained.getProducts().contains(product2));
    }

    @DisplayName("Given idCustomer and idProduct " +
            "When removeProduct is called" +
            "Then should return Without a Product ")
    @Test
    void removeProductFromNonExistentWishlist() {
        String idCustomer = "54759eb3c090d83494e2d804";
        String idProduct = "507f1f77bcf86cd799439011";
        when(this.customerWishlistRepository.findByCustomerId(idCustomer))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.customerWishlistMaintainServiceImpl.removeProduct(idCustomer, idProduct);
        });

        verify(this.customerWishlistRepository, times(1)).findByCustomerId(idCustomer);
        verifyNoMoreInteractions(this.customerWishlistRepository);
        assertEquals("Wishlist does not exist", exception.getMessage());
    }
}
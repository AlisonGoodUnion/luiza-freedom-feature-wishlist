package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.infrastructure.client.CustomerApiClientImpl;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
import com.luiza.demo.product.domain.model.Product;
import com.luiza.demo.product.infrastructure.client.ProductApiClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerWishlistMaintainServiceImpl implements CustomerWishlistMaintainService {

    private final CustomerWishlistRepository customerWishlistRepository;
    private final ProductApiClientImpl productApiClient;
    private final CustomerApiClientImpl customerApiClient;
    private final WishlistAddProductValidator wishlistAddProductValidator;

    @Autowired
    public CustomerWishlistMaintainServiceImpl(CustomerWishlistRepository customerWishlistRepository,
                                               ProductApiClientImpl productsProductApiClient,
                                               CustomerApiClientImpl customerApiClient,
                                               WishlistAddProductValidator wishlistAddProductValidator) {
        this.customerWishlistRepository = customerWishlistRepository;
        this.productApiClient = productsProductApiClient;
        this.customerApiClient = customerApiClient;
        this.wishlistAddProductValidator = wishlistAddProductValidator;
    }

    @Override
    public Wishlist addProduct(String idCustomer, String idProduct, int quantity) {
        quantityValidation(quantity);

        Optional<Wishlist> wishlistEntityOptional = this.obtainWishlist(idCustomer);
        if (wishlistEntityOptional.isEmpty()) {
            return createNewWishlist(idCustomer, idProduct, quantity);
        }

        Wishlist wishlistEntidy = wishlistEntityOptional.get();
        wishlistAddProductValidator.accept(wishlistEntidy, idProduct);

        return addProduct(idProduct, quantity, wishlistEntidy);
    }

    @Override
    public Wishlist removeProduct(String idCustomer, String idProduct) {
        Wishlist wishlist = this.obtainWishlist(idCustomer).stream()
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wishlist does not exist")
                );
        wishlist.removeProduct(idProduct);
        return this.customerWishlistRepository.save(wishlist);
    }

    private Optional<Wishlist> obtainWishlist(String idCustomer) {
        return this.customerWishlistRepository.findByCustomerId(idCustomer);
    }

    private void quantityValidation(int quantity) {
        if (quantity <= 0 || quantity > 999) {
            throw new ResourceNotFoundException("Invalid Quantity");
        }
    }

    private Wishlist addProduct(String idProduct, int quantity, Wishlist wishlistEntity) {
        Product product = this.productApiClient.findById(idProduct);
        product.addQuantity(quantity);
        wishlistEntity.addProduct(product);
        return this.customerWishlistRepository.save(wishlistEntity);
    }

    private Wishlist createNewWishlist(String idCustomer, String idProduct, int quantity) {
        Product product = this.productApiClient.findById(idProduct);
        product.addQuantity(quantity);

        Customer customer = this.customerApiClient.findById(idCustomer);
        return saveWishlist(customer, product);
    }

    private Wishlist saveWishlist(Customer customer, Product product) {
        List<Product> products = new ArrayList<>();
        products.add(product);

        Wishlist wishlist = Wishlist.builder()
                .customer(customer)
                .products(products)
                .build();

        return this.customerWishlistRepository.save(wishlist);
    }


}

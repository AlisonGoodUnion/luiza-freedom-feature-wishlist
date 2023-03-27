package com.luiza.demo.customer.wishlist.domain.service;

import com.luiza.demo.customer.domain.model.Customer;
import com.luiza.demo.customer.infrastructure.client.CustomerApiClientImpl;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.infrastructure.persistence.CustomerWishlistRepository;
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
    private final ProductApiClientImpl productsProductApiClient;
    private final CustomerApiClientImpl customerApiClient;

    @Autowired
    public CustomerWishlistMaintainServiceImpl(CustomerWishlistRepository customerWishlistRepository,
                                               ProductApiClientImpl productsProductApiClient,
                                               CustomerApiClientImpl customerApiClient) {
        this.customerWishlistRepository = customerWishlistRepository;
        this.productsProductApiClient = productsProductApiClient;
        this.customerApiClient = customerApiClient;
    }

    @Override
    public Wishlist addProduct(String idCustomer, String idProduct, int quantity) throws Exception {
        Optional<Wishlist> wishlistEntityOptional = this.customerWishlistRepository.findByCustomerId(idCustomer);

        if (wishlistEntityOptional.isEmpty()) {
            return createNewWishlist(idCustomer, idProduct, quantity);
        }

        Wishlist wishlistEntidy = wishlistEntityOptional.get();
        if (wishlistEntidy.getProducts().size() == 20) {
            throw new Exception("Exceeded product limit we can add only 20 products ");
        }

        return addWishlistProduct(idProduct, quantity, wishlistEntidy);
    }

    private Wishlist addWishlistProduct(String idProduct, int quantity, Wishlist wishlistEntity) {
        Product product = this.productsProductApiClient.findById(idProduct);
        product.addQuantity(quantity);
        wishlistEntity.addProduct(product);
        return this.customerWishlistRepository.save(wishlistEntity);
    }

    private Wishlist createNewWishlist(String idCustomer, String idProduct, int quantity) {
        Product product = this.productsProductApiClient.findById(idProduct);

        product.addQuantity(quantity);
        Customer customer = this.customerApiClient.findById(idCustomer).get();
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

    @Override
    public Wishlist removeProduct(String idCustomer, String idProduct) {
        Wishlist wishlist = this.customerWishlistRepository.findByCustomerId(idCustomer).stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("The server found nothing that matches the values used in the query")
                );
        wishlist.removeProduct(idProduct);
        return this.customerWishlistRepository.save(wishlist);
    }
}

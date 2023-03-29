package com.luiza.demo.customer.wishlist;

import com.luiza.demo.customer.CustomerDataProvider;
import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.product.ProductDataProvider;
import com.luiza.demo.product.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WishlistDataProvider {

    public static Wishlist getWishlist() {
        return Wishlist.builder()
                .customer(CustomerDataProvider.getCustomer())
                .products(List.of(ProductDataProvider.getProduct()))
                .build();
    }

    public static Wishlist getWishlistEntity() {
        Product product = ProductDataProvider.getProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);

        return Wishlist.builder()
                .id("64237090b89a0e1c1bbf2fc9")
                .customer(CustomerDataProvider.getCustomer())
                .products(products)
                .build();
    }

    public static Wishlist getWishlistEntityWith2Products() {
        Product product = ProductDataProvider.getProduct();
        Product product2 = ProductDataProvider.getProduct2();
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

        return Wishlist.builder()
                .id("64237090b89a0e1c1bbf2fc9")
                .customer(CustomerDataProvider.getCustomer())
                .products(products)
                .build();
    }
}

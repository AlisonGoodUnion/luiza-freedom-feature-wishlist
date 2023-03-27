package com.luiza.demo.customer.wishlist.presentation;

import com.luiza.demo.customer.wishlist.domain.service.CustomerWishlistQueryService;
import com.luiza.demo.product.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class CustomerWishlistQueryControllerImpl implements CustomerWishlistQueryController {

    private final CustomerWishlistQueryService customerWishlistQueryService;

    @Autowired
    public CustomerWishlistQueryControllerImpl(CustomerWishlistQueryService customerWishlistQueryService) {
        this.customerWishlistQueryService = customerWishlistQueryService;
    }

    @Override
    @GetMapping("/{id}/wishlists/products")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable(name = "id") final String idCustomer) {
        Optional<List<Product>> wishlist = this.customerWishlistQueryService.getAllProducts(idCustomer);
        return wishlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    @GetMapping("/{id}/wishlists/products/{idProduct}")
    public ResponseEntity<Product> getOneProduct(
            @PathVariable(name = "id") String idCustomer,
            @PathVariable(name = "idProduct") String idProduct) {

        Optional<Product> product = this.customerWishlistQueryService.getOneProduct(idCustomer, idProduct);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}

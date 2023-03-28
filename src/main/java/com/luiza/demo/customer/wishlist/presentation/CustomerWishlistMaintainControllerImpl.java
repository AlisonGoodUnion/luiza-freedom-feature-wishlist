package com.luiza.demo.customer.wishlist.presentation;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.domain.service.CustomerWishlistMaintainServiceImpl;
import com.luiza.demo.customer.wishlist.presentation.requestdto.WishlistRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerWishlistMaintainControllerImpl implements CustomerWishlistMaintainController {

    private final CustomerWishlistMaintainServiceImpl customerWishlistMaintainService;

    @Autowired
    public CustomerWishlistMaintainControllerImpl(CustomerWishlistMaintainServiceImpl customerWishlistMaintainService) {
        this.customerWishlistMaintainService = customerWishlistMaintainService;
    }

    @PostMapping("{id}/wishlists/products")
    @Override
    public ResponseEntity<Wishlist> addProduct(
            @PathVariable(name = "id") final String idCustomer,
            @RequestBody WishlistRequestDto wishlistRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerWishlistMaintainService.addProduct(
                        idCustomer,
                        wishlistRequestDto.getIdProduct(),
                        wishlistRequestDto.getQuantity()));
    }

    @DeleteMapping("{id}/wishlists/products{idProduct}")
    @Override
    public ResponseEntity<Wishlist> removeProduct(
            @PathVariable(name = "id") final String idCustomer,
            @PathVariable(name = "idProduct") final String idProduct) {
        Wishlist wishlist = this.customerWishlistMaintainService.removeProduct(idCustomer, idProduct);
        return ResponseEntity.ok(wishlist);
    }
}

package com.luiza.demo.customer.wishlist.presentation.requestdto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class WishlistRequestDto {
    private final String idProduct;

    @Positive()
    private final int quantity;
}

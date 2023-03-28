package com.luiza.demo.customer.wishlist.presentation;

import com.luiza.demo.customer.wishlist.domain.model.Wishlist;
import com.luiza.demo.customer.wishlist.presentation.requestdto.WishlistRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Customer's Wishlist Maintain", description = "Service used to maintain the customer's wishlist")
public interface CustomerWishlistMaintainController {
    @Operation(summary = "Add product in customer's wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "add product to the wishlist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Customer or Product does not exist or invalid quantity",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422",
                    description = "Domain Business Exception",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    ResponseEntity<Wishlist> addProduct(
            @Parameter(description = "Customer ID") final String idCustomer,
            @RequestBody WishlistRequestDto wishlistRequestDto
    );

    @Operation(summary = "Remove produt from customer's wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "remove product to the wishlist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Customer does not exist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    ResponseEntity<Wishlist> removeProduct(
            @Parameter(description = "Customer ID") final String idCustomer,
            @Parameter(description = "Product ID") final String idProduct
    );
}

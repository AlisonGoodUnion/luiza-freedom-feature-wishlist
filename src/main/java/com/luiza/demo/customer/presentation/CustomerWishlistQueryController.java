package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.util.Collection;

@Tag(name = "Customer's Wishlist", description = "Service used to consult the customer's wishlist")
public interface CustomerWishlistQueryController {

    @Operation(summary = "Get all the customer's wishlist produts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain all the products",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Wishlist not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    Collection<Product> getAll(@Parameter(description = "Customer ID") final String idCustomer);

    @Operation(summary = "See if a certain product is on the customer's Wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain the customer's wishlist product",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "The server found nothing that matches the values used in the query",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    Product getProduct(
            @Parameter(description = "Customer ID") final String idCustomer,
            @Parameter(description = "Product ID") final String idProduct
    );
}

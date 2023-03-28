package com.luiza.demo.product.presentation;

import com.luiza.demo.product.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Tag(name = "Products", description = "Service used to obtain products")
public interface ProductQueryController {

    @Operation(summary = "Get all the products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain all the products",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    ResponseEntity<List<Product>> getAll();

    @Operation(summary = "Obtain one product's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain the product",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Product does not exist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    ResponseEntity<Product> getOne(@Parameter(description = "Product ID") final String idProduct);
}

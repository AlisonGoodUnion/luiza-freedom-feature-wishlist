package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.util.List;


@Tag(name = "Products", description = "Service used to consult products")
public interface ProductQueryController {

    @Operation(summary = "Get all the products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain all the products",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    List<Product> getAll();
}

package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Tag(name = "Customers", description = "Service used to query customers")
public interface CustomerQueryController {

    @Operation(summary = "Get all the customer's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain all the customers",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    ResponseEntity<List<Customer>> getAll();

    @Operation(summary = "Obtain one customer's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain the customer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "Customer does not exist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    ResponseEntity<Customer> getOne(@Parameter(description = "Customer ID") final String idCustomer);
}

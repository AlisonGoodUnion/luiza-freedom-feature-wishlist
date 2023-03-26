package com.luiza.demo.customer.presentation;

import com.luiza.demo.customer.domain.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.util.List;


@Tag(name = "Customers", description = "Service used to consult customers")
public interface CustomerQueryController {

    @Operation(summary = "Get all the customer's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain all the customers",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    List<Customer> getAll();

    @Operation(summary = "Obtain one customer's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtain the customer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "The server found nothing that matches the values used in the query",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    Customer getOne(@Parameter(description = "Customer ID") final String idCustomer);
}

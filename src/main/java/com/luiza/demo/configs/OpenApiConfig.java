package com.luiza.demo.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wishlist API")
                        .version("1.0.0")
                        .description("Serviço utilizado para gerenciamento de Wishlist(lista de desejos) do cliente"));
    }
}


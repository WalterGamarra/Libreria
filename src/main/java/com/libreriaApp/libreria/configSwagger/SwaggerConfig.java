package com.libreriaApp.libreria.configSwagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TULibrería-API")
                        .version("1.0.0")
                        .description("API REST para gestión de una tienda de libros.")
                        .contact(new Contact()
                                .name("Walter Gamarra")
                                .email("waltergamarra572@gmail.com")
                                .url("https://github.com/WalterGamarra")
                        )
                );
    }
}
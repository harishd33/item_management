package com.demo.item_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI itemManagementOpenAPI() {
        Server productionServer = new Server();
        productionServer.setUrl("https://web-production-47ac5.up.railway.app");
        productionServer.setDescription("Production Server (Railway)");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setEmail("dsvjavalinux@gmail.com");
        contact.setName("Item Management API");

        Info info = new Info()
                .title("Item Management REST API")
                .version("1.0")
                .description("A RESTful API for managing items with in-memory storage. " +
                        "This API provides endpoints to create, retrieve, and manage items. " +
                        "Features include input validation, error handling, and comprehensive documentation.")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(productionServer, localServer));
    }
}


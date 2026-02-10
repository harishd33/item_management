package com.demo.item_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemManagementApplication.class, args);
		System.out.println("\n===========================================");
		System.out.println("Item Management API is running!");
		System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
		System.out.println("===========================================\n");
	}

}


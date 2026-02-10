package com.demo.item_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for Item Management REST API
 * 
 * This application provides RESTful endpoints for managing items with:
 * - In-memory ArrayList storage
 * - Input validation
 * - CRUD operations
 */
@SpringBootApplication
public class ItemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemManagementApplication.class, args);
		System.out.println("\n===========================================");
		System.out.println("Item Management API is running!");
		System.out.println("Access the API at: http://localhost:8080");
		System.out.println("===========================================\n");
	}

}


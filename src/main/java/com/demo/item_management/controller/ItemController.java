package com.demo.item_management.controller;

import com.demo.item_management.model.Item;
import com.demo.item_management.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Tag(name = "Item Management", description = "APIs for managing items with in-memory storage")
public class ItemController {
    
    private final ItemService itemService;
    
    @Operation(
        summary = "Create a new item",
        description = "Adds a new item to the in-memory storage. Item name and description are required."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Item created successfully",
            content = @Content(schema = @Schema(implementation = Item.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - validation failed"
        )
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> addItem(
            @Parameter(description = "Item object to be created", required = true)
            @Valid @RequestBody Item item) {
        Item createdItem = itemService.addItem(item);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Item created successfully");
        response.put("data", createdItem);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @Operation(
        summary = "Get item by ID",
        description = "Retrieves a specific item from storage by its unique ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Item found",
            content = @Content(schema = @Schema(implementation = Item.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Item not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getItemById(
            @Parameter(description = "ID of the item to retrieve", required = true, example = "1")
            @PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("data", item);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", false);
                    response.put("message", "Item not found with id: " + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }
    
    @Operation(
        summary = "Get all items",
        description = "Retrieves all items from the in-memory storage"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all items"
        )
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", items.size());
        response.put("data", items);
        
        return ResponseEntity.ok(response);
    }
    
    @Operation(
        summary = "Health check",
        description = "Check if the API is running and get the current item count"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "API is healthy and running"
        )
    })
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Item Management API");
        response.put("totalItems", itemService.getItemCount());
        
        return ResponseEntity.ok(response);
    }
}


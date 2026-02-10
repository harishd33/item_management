package com.demo.item_management.controller;

import com.demo.item_management.model.Item;
import com.demo.item_management.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for Item Management
 * Provides endpoints for creating and retrieving items
 */
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemService itemService;
    
    /**
     * Add a new item
     * 
     * POST /api/items
     * 
     * Request Body:
     * {
     *   "name": "Item Name",
     *   "description": "Item Description",
     *   "category": "Category Name",
     *   "price": 99.99
     * }
     * 
     * @param item The item to be added (validated)
     * @return ResponseEntity with the created item and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addItem(@Valid @RequestBody Item item) {
        Item createdItem = itemService.addItem(item);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Item created successfully");
        response.put("data", createdItem);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Get a single item by ID
     * 
     * GET /api/items/{id}
     * 
     * @param id The ID of the item to retrieve
     * @return ResponseEntity with the item if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getItemById(@PathVariable Long id) {
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
    
    /**
     * Get all items (bonus endpoint for convenience)
     * 
     * GET /api/items
     * 
     * @return ResponseEntity with list of all items
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", items.size());
        response.put("data", items);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Health check endpoint
     * 
     * GET /api/items/health
     * 
     * @return ResponseEntity with health status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Item Management API");
        response.put("totalItems", itemService.getItemCount());
        
        return ResponseEntity.ok(response);
    }
}


package com.demo.item_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Item Model representing an item with its properties
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    /**
     * Unique identifier for the item
     */
    private Long id;
    
    /**
     * Name of the item (required)
     */
    @NotBlank(message = "Item name is required")
    private String name;
    
    /**
     * Description of the item (required)
     */
    @NotBlank(message = "Item description is required")
    private String description;
    
    /**
     * Category of the item
     */
    private String category;
    
    /**
     * Price of the item
     */
    private Double price;
    
    /**
     * Timestamp when the item was created
     */
    private Long createdAt;
}


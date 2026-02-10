package com.demo.item_management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Item entity representing a product or object in the system")
public class Item {
    
    @Schema(
        description = "Unique identifier for the item (auto-generated)",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;
    
    @Schema(
        description = "Name of the item",
        example = "Laptop",
        required = true
    )
    @NotBlank(message = "Item name is required")
    private String name;
    
    @Schema(
        description = "Detailed description of the item",
        example = "High-performance gaming laptop with RTX 4080",
        required = true
    )
    @NotBlank(message = "Item description is required")
    private String description;
    
    @Schema(
        description = "Category or type of the item",
        example = "Electronics"
    )
    private String category;
    
    @Schema(
        description = "Price of the item in USD",
        example = "1299.99"
    )
    private Double price;
    
    @Schema(
        description = "Timestamp when the item was created (auto-generated)",
        example = "1707580800000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long createdAt;
}


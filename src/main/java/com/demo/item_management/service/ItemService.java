package com.demo.item_management.service;

import com.demo.item_management.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing items using in-memory ArrayList storage
 */
@Service
public class ItemService {
    
    // In-memory data store using ArrayList
    private final List<Item> items = new ArrayList<>();
    
    // Auto-incrementing ID generator
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Add a new item to the data store
     * @param item The item to be added (without ID)
     * @return The created item with generated ID
     */
    public Item addItem(Item item) {
        // Generate a unique ID for the item
        item.setId(idGenerator.getAndIncrement());
        
        // Set creation timestamp
        item.setCreatedAt(System.currentTimeMillis());
        
        // Add to the ArrayList
        items.add(item);
        
        return item;
    }
    
    /**
     * Get a single item by its ID
     * @param id The ID of the item to retrieve
     * @return Optional containing the item if found, empty otherwise
     */
    public Optional<Item> getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Get all items (for debugging/testing purposes)
     * @return List of all items
     */
    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }
    
    /**
     * Get the total count of items
     * @return The number of items in the store
     */
    public int getItemCount() {
        return items.size();
    }
}


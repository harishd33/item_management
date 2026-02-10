package com.demo.item_management.service;

import com.demo.item_management.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {
    
    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public Item addItem(Item item) {
        item.setId(idGenerator.getAndIncrement());
        item.setCreatedAt(System.currentTimeMillis());
        items.add(item);
        return item;
    }
    
    public Optional<Item> getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }
    
    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }
    
    public int getItemCount() {
        return items.size();
    }
}


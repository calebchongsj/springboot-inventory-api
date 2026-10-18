package com.example.inventory.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.inventory.exception.InventoryItemNotFoundException;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
    private final InventoryRepository repository;

    // Constructor dependency injection
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    // Method 1: Add a new inventory item
    public InventoryItem addItem(InventoryItem item) {
        // Step 1: Check whether the item is null
        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null"
            );
        }

        // Step 2: Prevent client-supplied IDs
        if (item.getId() != null) {
            throw new IllegalArgumentException(
                "New inventory items must not have an ID"
            );
        }

        // Step 3: Validate the item name
        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException(
                "Item name cannot be empty"
            );
        }

        // Step 4: Validate the quantity
        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative"
            );
        }

        // Step 5: Save the item to MongoDB
        return repository.save(item);
    }

    // Method 2: Retrieve all inventory items
    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    // Method 3: Update an item's quantity
    public InventoryItem updateQuantity(String id, int newQuantity) {
        // Step 1: Validate the new quantity
        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative"
            );
        }

        // Step 2: Find the existing item
        Optional<InventoryItem> result = repository.findById(id);

        // Check if item is not in MongoDB
        if (result.isEmpty()) {
            throw new InventoryItemNotFoundException("Inventory item not found");
        }

        // Retrieve the object
        InventoryItem item = result.get();

        // Step 3: Update the object's quantity
        item.setQuantity(newQuantity);

        // Step 4: Save the updated item
        return repository.save(item);
    }
}

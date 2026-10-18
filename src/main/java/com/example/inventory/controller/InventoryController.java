package com.example.inventory.controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/items")
public class InventoryController {
    private final InventoryService service;

    // Constructor dependency injection
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // Endpoint 1: Add an inventory item
    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return service.addItem(item);
    }

    // Endpoint 2: Retrieve all inventory items
    @GetMapping
    public List<InventoryItem> getAllItems() {
        return service.getAllItems();
    }

    // Endpoint 3: Update an item's quantity
    @PatchMapping("/{id}/quantity")
    public InventoryItem updateQuantity(@PathVariable String id, @RequestParam int quantity) {
        return service.updateQuantity(id, quantity);
    }
}

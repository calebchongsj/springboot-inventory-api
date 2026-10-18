import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    // Stores inventory items temporarily
    private List<InventoryItem> items;

    // Constructor
    public InventoryService() {
        this.items = new ArrayList<>();
    }

    // Method 1: Add an inventory item
    public void addItem(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null"
            );
        }

        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative"
            );
        }
        items.add(item);
    }

    // Method 2: Retrieve all inventory items
    public List<InventoryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    // Method 3: Update quantity by item ID
    public void updateQuantity(int id, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative"
            );
        }

        for (InventoryItem item : items) {
            if (item.getId() == id) {
                item.setQuantity(newQuantity);
                return;
            }
        }

        throw new IllegalArgumentException(
            "Inventory item not found"
        );
    }
}

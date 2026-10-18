public class InventoryItem {
    private int id;
    private String name;
    private int quantity;

    // Constructor
    public InventoryItem(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Get item by ID
    public int getId() {
        return id;
    }

    // Get item by name
    public String getName() {
        return name;
    }

    // Get item by quantity
    public int getQuantity() {
        return quantity;
    }

    // Set item to a fixed quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

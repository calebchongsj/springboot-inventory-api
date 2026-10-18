public class Main {
    public static void main(String[] args) {
        // Step 1: Create the service
        InventoryService service = new InventoryService();

        // Step 2: Create inventory objects
        InventoryItem laptop = new InventoryItem(1, "Laptop", 10);
        InventoryItem mouse = new InventoryItem(2, "Mouse", 25);

        // Step 3: Add items to inventory
        service.addItem(laptop);
        service.addItem(mouse);

        // Step 4: Display inventory
        for (InventoryItem item : service.getAllItems()) {
            System.out.println(
                item.getName() + ": " + item.getQuantity()
            );
        }

        // Step 5: Update laptop quantity
        service.updateQuantity(1, 15);

        // Step 6: Display updated quantity
        System.out.println(
            "Updated laptop quantity: " + laptop.getQuantity()
        );
    }
}

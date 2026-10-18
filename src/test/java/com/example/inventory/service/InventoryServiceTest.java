package com.example.inventory.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.repository.InventoryRepository;

public class InventoryServiceTest {
    private InventoryRepository repository;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(InventoryRepository.class);
        service = new InventoryService(repository);
    }

    @Test
    void addItemRejectsNegativeQuantity() {
        InventoryItem item = new InventoryItem("Laptop", -5);

        assertThrows(
            IllegalArgumentException.class,
            () -> service.addItem(item)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void updateQuantityChangesExistingItem() {
        InventoryItem item = new InventoryItem("Laptop", 10);
        String id = "test123";

        when(repository.findById(id)).thenReturn(Optional.of(item));

        when(repository.save(item)).thenReturn(item);

        InventoryItem updated = service.updateQuantity(id, 15);

        assertEquals(15, updated.getQuantity());

        verify(repository).save(item);
    }
}

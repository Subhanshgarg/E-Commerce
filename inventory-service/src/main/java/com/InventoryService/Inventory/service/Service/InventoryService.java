package com.InventoryService.Inventory.service.Service;

import com.InventoryService.Inventory.service.Repository.InventoryRepository;
import com.InventoryService.Inventory.service.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public boolean isInStock(String skuCode) {
        Optional<Inventory> inventoryOptional= inventoryRepository.findBySkuCode(skuCode);
        if(inventoryOptional.isPresent() &&  inventoryOptional.get().getQuantity()>0)
                return true;
        return false;
    }
}

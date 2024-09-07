package com.InventoryService.Inventory.service.Controller;

import com.InventoryService.Inventory.service.DTO.InventoryResponse;
import com.InventoryService.Inventory.service.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/")
@RequiredArgsConstructor
public class InventoryController{
    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}

package com.space.server.core.inventory.presentation;

import com.space.server.core.inventory.presentation.dto.request.CreateInventoryRequest;
import com.space.server.core.inventory.presentation.dto.response.InventoryResponse;
import com.space.server.core.inventory.service.CommandInventoryService;
import com.space.server.core.inventory.service.QueryInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

  private final CommandInventoryService commandInventoryService;
  private final QueryInventoryService queryInventoryService;

  @PostMapping("/inventory")
  public void createInventory(@RequestBody CreateInventoryRequest request) {
    commandInventoryService.createInventory(request.user(), request.itemId());
  }

  @GetMapping("/{inventory-id}")
  public InventoryResponse readOne(@PathVariable("inventory-id") Long inventoryId) {
    return InventoryResponse.from(queryInventoryService.readOne(inventoryId));
  }

  @PutMapping("/{inventory-id}/{item-id}")
  public void updateInventory(
      @PathVariable("inventory-id") Long inventoryId,
      @PathVariable("item-id") Long itemId
  ) {
    commandInventoryService.updateInventory(inventoryId, itemId);
  }

  @DeleteMapping("/{inventory-id}")
  public void deleteInventory(@PathVariable(name = "inventory-id") Long inventoryId) {
    commandInventoryService.deleteInventory(inventoryId);
  }
}

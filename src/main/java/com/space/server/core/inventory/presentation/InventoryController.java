package com.space.server.core.inventory.presentation;

import com.space.server.core.inventory.presentation.dto.request.InventoryRequest;
import com.space.server.core.inventory.presentation.dto.response.InventoryResponse;
import com.space.server.core.inventory.service.CommandInventoryService;
import com.space.server.core.inventory.service.QueryInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

  private final CommandInventoryService commandInventoryService;
  private final QueryInventoryService queryInventoryService;

  @PostMapping("/inventory")
  public void createInventory(@RequestBody InventoryRequest request) {
    commandInventoryService.createInventory(request.itemId(), SecurityContextHolder.getContext().getAuthentication());
  }

  @GetMapping("/{inventory-id}")
  public InventoryResponse readOne(@PathVariable("inventory-id") Long inventoryId) {
    return InventoryResponse.from(queryInventoryService.readOne(inventoryId));
  }

  @GetMapping
  public List<InventoryResponse> readAll() {
    return queryInventoryService.readMine(SecurityContextHolder.getContext().getAuthentication()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @GetMapping("/equip")
  public List<InventoryResponse> readIsEquipped() {
    return queryInventoryService.readIsEquipped(SecurityContextHolder.getContext().getAuthentication()).stream()
        .map((InventoryResponse::from))
        .toList();
  }

  @PutMapping("/{inventory-id}")
  public void equipItem(@PathVariable(name = "inventory-id") Long inventoryId) {
    commandInventoryService.equipInventory(inventoryId, SecurityContextHolder.getContext().getAuthentication());
  }

  @DeleteMapping("/{inventory-id}")
  public void deleteInventory(@PathVariable(name = "inventory-id") Long inventoryId) {
    commandInventoryService.deleteInventory(inventoryId);
  }
}

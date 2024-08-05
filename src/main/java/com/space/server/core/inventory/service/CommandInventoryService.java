package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryCreator;
import com.space.server.core.inventory.service.implementation.InventoryDeleter;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.core.inventory.service.implementation.InventoryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandInventoryService {

  private final InventoryCreator inventoryCreator;
  private final InventoryUpdater inventoryUpdater;
  private final InventoryReader inventoryReader;

  public void createInventory(Inventory inventory) {
    inventoryCreator.create(inventory);
  }

  public void updateInventory(Long inventoryId, Inventory inventory) {
    Inventory updatableInventory = inventoryReader.read(inventoryId);
    inventoryUpdater.update(updatableInventory, inventory);
  }
}

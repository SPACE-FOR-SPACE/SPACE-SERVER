package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryCreator;
import com.space.server.core.inventory.service.implementation.InventoryDeleter;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.core.inventory.service.implementation.InventoryUpdater;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.service.implementation.ItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandInventoryService {

  private final InventoryCreator inventoryCreator;
  private final InventoryDeleter inventoryDeleter;
  private final InventoryUpdater inventoryUpdater;
  private final InventoryReader inventoryReader;
  private final ItemReader itemReader;

  public void createInventory(Inventory inventory) {
    inventoryCreator.create(inventory);
  }

  public void updateInventory(Long inventoryId, Long itemId) {
    Item item = itemReader.read(itemId);
    inventoryUpdater.update(inventoryReader.read(inventoryId), item);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.read(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

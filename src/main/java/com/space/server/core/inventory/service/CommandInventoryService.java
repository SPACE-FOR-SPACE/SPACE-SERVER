package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.*;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.service.implementation.ItemReader;
import com.space.server.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandInventoryService {

  private final InventoryCreator inventoryCreator;
  private final InventoryReader inventoryReader;
  private final InventoryUpdater inventoryUpdater;
  private final InventoryDeleter inventoryDeleter;
  private final InventoryValidator inventoryValidator;
  private final ItemReader itemReader;

  public void createInventory(Long itemId, User user) {
    Inventory inventory = new Inventory(user);
    Item item = itemReader.findById(itemId);
    inventoryUpdater.update(inventory, item);
    inventoryCreator.create(inventory);
  }

  public void buyItem(Long itemId, User user) {
    Item item = itemReader.findById(itemId);
    inventoryValidator.hasItem(item, user);
    inventoryValidator.canBuyItem(item, user);
    Inventory inventory = new Inventory(user);
    inventoryUpdater.update(inventory, item);
    inventoryCreator.create(inventory);
  }

  public void equipInventory(Long itemId, User user) {
    Item item = itemReader.findById(itemId);
    Inventory inventory = inventoryReader.findByItemAndUser(item, user);
    inventoryValidator.hasNotItem(item, user);
    inventoryUpdater.update(inventory, item);

    Inventory unequipInventory = inventoryReader.findByCategoryAndUserAndIsEquipped(item.getCategory(), user);
    inventoryUpdater.unEquip(unequipInventory);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.findById(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

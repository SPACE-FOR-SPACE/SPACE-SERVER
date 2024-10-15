package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.*;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import com.space.server.core.item.service.implementation.ItemReader;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
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
  private final UserRepository userRepository;

  public void createInventory(Long itemId, Long userId) {
    Users user = userRepository.findById(userId)
        .orElse(null);
    Item item = itemReader.findById(itemId);
    Inventory inventory = new Inventory(item, user);
    inventoryUpdater.equip(inventory);
    inventoryCreator.create(inventory);
  }

  public void buyItem(Long itemId, Long userId) {
    Users user = userRepository.findById(userId)
        .orElse(null);
    Item item = itemReader.findById(itemId);
    inventoryValidator.hasItem(item, user);
    inventoryValidator.buyItem(item, user);
    Inventory inventory = new Inventory(item, user);
    inventoryCreator.create(inventory);
  }

  public void equipInventory(Long inventoryId, Long userId) {
    Users user = userRepository.findById(userId)
        .orElse(null);
    Inventory inventory = inventoryReader.findById(inventoryId);
    Category category = inventory.getItem().getCategory();
    Inventory unequipInventory = inventoryReader.findByCategoryAndUserAndIsEquipped(category, user);
    inventoryUpdater.unEquip(unequipInventory);
    inventoryUpdater.equip(inventory);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.findById(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

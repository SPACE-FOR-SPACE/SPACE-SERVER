package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.*;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.service.implementation.ItemReader;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

  public void createInventory(Long itemId, Authentication email) {
    System.out.println("email = " + email);
    Item item = itemReader.findById(itemId);
    Users user = userRepository.findByEmail(String.valueOf(email));
    Inventory inventory = new Inventory(item, user);
    inventoryCreator.create(inventory);
  }

  public void buyItem(Long itemId, Object email) {
    Item item = itemReader.findById(itemId);
    Users user = userRepository.findByEmail(email.toString());
    inventoryValidator.hasItem(item, user);
//    inventoryValidator.canBuyItem(item, user);
    Inventory inventory = new Inventory(item, user);
    inventoryCreator.create(inventory);
  }

  public void equipInventory(Long inventoryId, Object email) {
    Inventory inventory = inventoryReader.findById(inventoryId);
    Item item = inventory.getItem();
    Users user = userRepository.findByEmail(email.toString());
    inventoryUpdater.equip(inventory);

    Inventory unequipInventory = inventoryReader.findByCategoryAndUserAndIsEquipped(item.getCategory(), user);
    inventoryUpdater.unEquip(unequipInventory);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.findById(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

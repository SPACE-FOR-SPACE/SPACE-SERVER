package com.space.server.core.inventory.service;

import com.space.server.auth.service.dto.CustomUserDetails;
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

  public void createInventory(Long itemId, Authentication auth) {
    CustomUserDetails details = (CustomUserDetails) auth.getPrincipal();
    Users user = userRepository.findByEmail(details.getEmail());
    Item item = itemReader.findById(itemId);
    Inventory inventory = new Inventory(item, user);
    inventoryUpdater.equip(inventory);
    inventoryCreator.create(inventory);
  }

  public void buyItem(Long itemId, Authentication auth) {
    CustomUserDetails details = (CustomUserDetails) auth.getPrincipal();
    Users user = userRepository.findByEmail(details.getEmail());
    Item item = itemReader.findById(itemId);
    inventoryValidator.hasItem(item, user);
//    inventoryValidator.canBuyItem(item, user);
    Inventory inventory = new Inventory(item, user);
    inventoryCreator.create(inventory);
  }

  public void equipInventory(Long inventoryId, Authentication auth) {
    CustomUserDetails details = (CustomUserDetails) auth.getPrincipal();
    Users user = userRepository.findByEmail(details.getEmail());
    Inventory inventory = inventoryReader.findById(inventoryId);
    Item item = inventory.getItem();
    Inventory unequipInventory = inventoryReader.findByCategoryAndUserAndIsEquipped(item.getCategory(), user);
    inventoryUpdater.unEquip(unequipInventory);
    inventoryUpdater.equip(inventory);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.findById(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

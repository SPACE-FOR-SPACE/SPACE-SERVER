package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryCreator;
import com.space.server.core.inventory.service.implementation.InventoryDeleter;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.core.inventory.service.implementation.InventoryUpdater;
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
  private final InventoryDeleter inventoryDeleter;
  private final InventoryUpdater inventoryUpdater;
  private final InventoryReader inventoryReader;
  private final ItemReader itemReader;

  public void createInventory(Long itemId, User user) {
    Inventory inventory = new Inventory(user);
    Item item = itemReader.read(itemId);
    addItemToInventory(inventory, item);
    inventoryCreator.create(inventory);
  }

  public void buyItem(Long itemId, User user) {
    Item item = itemReader.read(itemId);

    if(isItemInInventory(user, item)) {
      throw new IllegalArgumentException("이미 존재하는 아이템");
    }

    if(item.getPrice() > user.getPoint()) {
      throw new IllegalStateException("포인트 부족");
    }
    else {
      Inventory inventory = new Inventory(user);
      addItemToInventory(inventory, item);
      inventoryCreator.create(inventory);
    }
  }

  public void updateInventory(User user, Long inventoryId, Long itemId) {
    Item item = itemReader.read(itemId);
    Inventory inventory = inventoryReader.read(inventoryId);

    if(!isItemInInventory(user, item)) {
      throw new IllegalStateException("해당 아이템을 가지고 있지 않습니다.");
    }

    inventoryUpdater.update(inventory, item);

    Inventory unequippedInventory = inventoryReader.findByUserAndCategoryAndIsEquipped(new User(), item.getCategory());
    inventoryUpdater.unEquip(unequippedInventory);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.read(inventoryId);
    inventoryDeleter.delete(inventory);
  }

  private void addItemToInventory(Inventory inventory, Item item) {
    inventoryUpdater.update(inventory, item);
  }

  private boolean isItemInInventory(User user, Item item) {
    return inventoryReader.findByUserAndItem(user, item);
  }
}

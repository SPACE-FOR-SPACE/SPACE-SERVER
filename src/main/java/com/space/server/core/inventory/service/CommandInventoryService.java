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

  public void createInventory(User user, Long itemId) {
    Inventory inventory = new Inventory(user);
    Item item = itemReader.read(itemId);
    addItemToInventory(inventory, item);
    inventoryCreator.create(inventory);
  }

  private void addItemToInventory(Inventory inventory, Item item) {
    inventoryUpdater.update(inventory, item);
  }

  public void buyItem(Long itemId, User user) {
    Item item = itemReader.read(itemId);

    if(inventoryReader.findByUserAndInventory(user, item)) {
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

  public void updateInventory(Long inventoryId, Long itemId) {
    Item item = itemReader.read(itemId);
    inventoryUpdater.update(inventoryReader.read(inventoryId), item);
  }

  public void deleteInventory(Long inventoryId) {
    Inventory inventory = inventoryReader.read(inventoryId);
    inventoryDeleter.delete(inventory);
  }
}

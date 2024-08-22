package com.space.server.core.inventory.service.implementation;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryUpdater {

  public void update(Inventory updatableInventory, Item item) {
    updatableInventory.update(item);
  }
}

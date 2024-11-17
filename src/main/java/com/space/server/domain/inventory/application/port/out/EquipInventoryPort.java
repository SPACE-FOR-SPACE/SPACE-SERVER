package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.value.Category;

public interface EquipInventoryPort {

  Inventory findEquippedInventoryByCategoryAndUser(Category category, Long userId);

  void equipInventory(Inventory inventory);

  void unEquipInventory(Inventory inventory);
}

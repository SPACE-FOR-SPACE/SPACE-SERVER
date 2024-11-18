package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;

public interface EquipInventoryPort {

  void equipInventory(Inventory inventory);

  void unEquipInventory(Inventory inventory);
}

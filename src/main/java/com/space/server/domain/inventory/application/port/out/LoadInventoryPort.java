package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;

public interface LoadInventoryPort {

  Inventory loadInventory(Long inventoryId);

}

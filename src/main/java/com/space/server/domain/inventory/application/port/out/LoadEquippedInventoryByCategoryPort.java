package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.value.Category;

public interface LoadEquippedInventoryByCategoryPort {

  Inventory findEquippedInventoryByCategoryAndUser(Category category, Long userId);
}

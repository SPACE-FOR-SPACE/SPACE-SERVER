package com.space.server.domain.inventory.application.port.in;

public interface EquipInventoryUseCase {

  void equipInventory(Long InventoryId, Long userId);
}
